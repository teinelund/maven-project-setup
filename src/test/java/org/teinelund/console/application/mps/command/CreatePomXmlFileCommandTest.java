package org.teinelund.console.application.mps.command;

import org.apache.commons.lang3.SystemUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.teinelund.console.application.mps.argumentparser.ArgumentsVO;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class CreatePomXmlFileCommandTest {

    private static String NAME = "NAME";
    private static String GROUP_ID = "GROUP_ID";
    private static String ARTIFACT_ID = "ARTIFACT_ID";
    private static String DESCRIPTION = "DESCRIPTION";

    @Test
    void updatePomXmlDocument() throws IOException, DocumentException {
        // initialize
        CreatePomXmlFileCommand sut = new CreatePomXmlFileCommand(null);
        Context context = new Context();
        ArgumentsVO arguments = new ArgumentsVO.Builder().setApplicationName(NAME).setGroupId(GROUP_ID).
                setArtifactId(ARTIFACT_ID).setDescription(DESCRIPTION).build();
        context.setArguments(arguments);
        // test
        sut.updatePomXmlDocument(createPomXmlDocument(), context);
        // verify
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