package org.teinelund.console.application.mps.command;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CreatePomXmlFileCommand extends AbstractCommand {
    public CreatePomXmlFileCommand(AbstractCommand nextCommand) {
        super(nextCommand);
    }

    @Override
    protected void action(Context context) throws IOException, DocumentException {
        Document pomXmlDocument = readPomTemplateXmlFile(context);
        updatePomXmlDocument(pomXmlDocument, context);
        savePomXmlDocument(pomXmlDocument, context);
    }

    Document readPomTemplateXmlFile(Context context) throws DocumentException {
        File inputFile = new File(context.getMavenPomXmlTemplateFile().toAbsolutePath().toString());
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputFile);
        return document;
    }

    void updatePomXmlDocument(Document pomXmlDocument, Context context) {
        Element root = pomXmlDocument.getRootElement();
        Element groupId = root.element("groupId");
        groupId.setText(context.getArguments().getGroupId());
        Element artifactId = root.element("artifactId");
        artifactId.setText(context.getArguments().getArtifactId());
        Element version = root.element("version");
        version.setText("1.0-SNAPSHOT");
        Node name = root.element("name");
        if (StringUtils.isBlank(context.getArguments().getApplicationName())) {
            name.setText(context.getArguments().getArtifactId());
        }
        else {
            name.setText(context.getArguments().getApplicationName());
        }
        Node description = root.element("description");
        if (StringUtils.isBlank(context.getArguments().getDescription())) {
            root.remove(description);
        }
        else {
            description.setText(context.getArguments().getDescription());
        }
    }

    void savePomXmlDocument(Document document, Context context) throws IOException {
        Path pomXmlFile = Paths.get(context.getApplicationDirectory().toAbsolutePath().toString(), "pom.xml");
        try (FileWriter fileWriter = new FileWriter(pomXmlFile.toString())) {
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(fileWriter, format);
            writer.write( document );
            writer.close();
        }
    }
}
