package org.teinelund.console.application.mps.command;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        Node groupId = pomXmlDocument.selectSingleNode("//project/groupId");
        groupId.setText(context.getArguments().getGroupId());
        Node artifactId = pomXmlDocument.selectSingleNode("//project/artifactId");
        artifactId.setText(context.getArguments().getArtifactId());
        Node version = pomXmlDocument.selectSingleNode("//project/version");
        version.setText("1.0-SNAPSHOT");
        Node name = pomXmlDocument.selectSingleNode("//project/name");
        if (StringUtils.isBlank(context.getArguments().getApplicationName())) {
            name.setText("");
        }
        else {
            name.setText(context.getArguments().getApplicationName());
        }
        Node description = pomXmlDocument.selectSingleNode("//project/description");
        if (StringUtils.isBlank(context.getArguments().getDescription())) {
            description.setText("");
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
