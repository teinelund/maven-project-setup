package org.teinelund.console.application.mps.command;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.junit.jupiter.api.Test;
import org.teinelund.console.application.mps.argumentparser.ArgumentsVO;

import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;

class CreatePomXmlFileCommandTest {

    private static String NAME = "NAME";
    private static String GROUP_ID = "GROUP_ID";
    private static String ARTIFACT_ID = "ARTIFACT_ID";
    private static String DESCRIPTION = "DESCRIPTION";
    private static String VERSION = "1.0-SNAPSHOT";

    @Test
    void updatePomXmlDocumentWhereNameAndDescriptionIsSpecified() throws IOException, DocumentException {
        // initialize
        CreatePomXmlFileCommand sut = new CreatePomXmlFileCommand(null);
        Context context = new Context();
        ArgumentsVO arguments = new ArgumentsVO.Builder().setGroupId(GROUP_ID).
                setArtifactId(ARTIFACT_ID).setApplicationName(NAME).setDescription(DESCRIPTION).build();
        context.setArguments(arguments);
        Document document = createPomXmlDocument();
        // test
        sut.updatePomXmlDocument(document, context);
        // verify
        Element root = document.getRootElement();
        Element groupId = root.element("groupId");
        assertThat(groupId.getText()).isEqualTo(GROUP_ID);
        Element artifactId = root.element("artifactId");
        assertThat(artifactId.getText()).isEqualTo(ARTIFACT_ID);
        Element version = root.element("version");
        assertThat(version.getText()).isEqualTo(VERSION);
        Node name = root.element("name");
        assertThat(name.getText()).isEqualTo(NAME);
        Node description = root.element("description");
        assertThat(description.getText()).isEqualTo(DESCRIPTION);
    }

    @Test
    void updatePomXmlDocumentWhereNameAndDescriptionIsNotSpecified() throws IOException, DocumentException {
        // initialize
        CreatePomXmlFileCommand sut = new CreatePomXmlFileCommand(null);
        Context context = new Context();
        ArgumentsVO arguments = new ArgumentsVO.Builder().setGroupId(GROUP_ID).
                setArtifactId(ARTIFACT_ID).build();
        context.setArguments(arguments);
        Document document = createPomXmlDocument();
        // test
        sut.updatePomXmlDocument(document, context);
        // verify
        Element root = document.getRootElement();
        Element groupId = root.element("groupId");
        assertThat(groupId.getText()).isEqualTo(GROUP_ID);
        Element artifactId = root.element("artifactId");
        assertThat(artifactId.getText()).isEqualTo(ARTIFACT_ID);
        Element version = root.element("version");
        assertThat(version.getText()).isEqualTo(VERSION);
        Node name = root.element("name");
        assertThat(name.getText()).isEqualTo(ARTIFACT_ID);
        Node description = root.element("description");
        assertThat(description).isNull();
    }

    Document createPomXmlDocument() throws DocumentException, IOException {
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append(System.lineSeparator());
        xml.append("<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
        xml.append(System.lineSeparator());
        xml.append("         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd\">");
        xml.append(System.lineSeparator());
        xml.append("    <modelVersion>4.0.0</modelVersion>");
        xml.append(System.lineSeparator());
        xml.append("    <groupId>${groupId}</groupId>");
        xml.append(System.lineSeparator());
        xml.append("    <artifactId>${artifactId}</artifactId>");
        xml.append(System.lineSeparator());
        xml.append("    <packaging>jar</packaging>");
        xml.append(System.lineSeparator());
        xml.append("    <version>${version}</version>");
        xml.append(System.lineSeparator());
        xml.append("    <name>${name}</name>");
        xml.append(System.lineSeparator());
        xml.append("    <description>${description}</description>");
        xml.append(System.lineSeparator());
        xml.append("    <properties>");
        xml.append(System.lineSeparator());
        xml.append("    </properties>");
        xml.append(System.lineSeparator());
        xml.append("    <dependencies>");
        xml.append(System.lineSeparator());
        xml.append("    </dependencies>");
        xml.append(System.lineSeparator());
        xml.append("    <build>");
        xml.append(System.lineSeparator());
        xml.append("        <plugins>");
        xml.append(System.lineSeparator());
        xml.append("        </plugins>");
        xml.append(System.lineSeparator());
        xml.append("    </build>");
        xml.append(System.lineSeparator());
        xml.append("</project>");
        Document document = DocumentHelper.parseText(xml.toString());
        return document;
    }
}