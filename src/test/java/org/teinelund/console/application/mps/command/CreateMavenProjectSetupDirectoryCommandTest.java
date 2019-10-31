package org.teinelund.console.application.mps.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CreateMavenProjectSetupDirectoryCommandTest {

    private Context context = null;

    @TempDir
    File homeDir;

    @BeforeEach
    void initTest() {
        this.context = new Context();
    }

    @Test
    void actionWhereDirectoryOfApplicationExist() throws IOException {
        // Initialize
        createFilesAndFolders();
        CreateMavenProjectSetupDirectoryCommand sut = new CreateMavenProjectSetupDirectoryCommandMock(null, this.homeDir);
        assertThat(doesTheDirectoryMavenProjectSetupExist()).isTrue();
        assertThat(doesFileExistInDirectoryMavenProjectSetup("pom-template.xml")).isTrue();
        // Test
        sut.action(context);
        // Verify
        assertThat(doesTheDirectoryMavenProjectSetupExist()).isTrue();
        assertThat(doesFileExistInDirectoryMavenProjectSetup("pom-template.xml")).isTrue();
        assertThat(isPomTemplateXmlFileValid()).isTrue();
    }

    @Test
    void actionWhereDirectoryOfApplicationDoesNotExist() throws IOException {
        // Initialize
        CreateMavenProjectSetupDirectoryCommand sut = new CreateMavenProjectSetupDirectoryCommandMock(null, this.homeDir);
        assertThat(doesTheDirectoryMavenProjectSetupExist()).isFalse();
        assertThat(doesFileExistInDirectoryMavenProjectSetup("pom-template.xml")).isFalse();
        // Test
        sut.action(context);
        // Verify
        assertThat(doesTheDirectoryMavenProjectSetupExist()).isTrue();
        assertThat(doesFileExistInDirectoryMavenProjectSetup("pom-template.xml")).isTrue();
        assertThat(isPomTemplateXmlFileValid()).isTrue();
    }

    void createFilesAndFolders() throws IOException {
        Path mavenProjectSetupDirectory = Paths.get(this.homeDir.getAbsolutePath(), ".maven-project-setup");
        Files.createDirectory(mavenProjectSetupDirectory);
        Path mavenPomXmlTemplateFile = Paths.get(mavenProjectSetupDirectory.toAbsolutePath().toString(), "pom-template.xml");
        Files.write(mavenPomXmlTemplateFile, createPomXmlTemplateContent().getBytes(Charset.forName("UTF-8")), StandardOpenOption.CREATE_NEW);
    }

    String createPomXmlTemplateContent() {
        StringBuilder pomxml = new StringBuilder();
        pomxml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        pomxml.append(System.lineSeparator());
        pomxml.append("<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
        pomxml.append(System.lineSeparator());
        pomxml.append("         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd\">");
        pomxml.append(System.lineSeparator());
        pomxml.append("    <modelVersion>4.0.0</modelVersion>");
        pomxml.append(System.lineSeparator());
        pomxml.append("</project>");
        return pomxml.toString();
    }


    boolean doesTheDirectoryMavenProjectSetupExist() {
        Path mavenProjectSetupDirectory = Paths.get(this.homeDir.getAbsolutePath(), ".maven-project-setup");
        return Files.exists(mavenProjectSetupDirectory);
    }

    boolean doesFileExistInDirectoryMavenProjectSetup(String filename) {
        Path file = Paths.get(this.homeDir.getAbsolutePath(), ".maven-project-setup", filename);
        return Files.exists(file);
    }

    boolean isPomTemplateXmlFileValid() throws IOException {
        Path pomTemplateXmlFile = Paths.get(this.homeDir.getAbsolutePath(), ".maven-project-setup", "pom-template.xml");
        List<String> lines = Files.readAllLines(pomTemplateXmlFile, Charset.forName("UTF-8"));
        boolean xmlVersionFound = false;
        boolean modelVersionTagFound = false;
        for (String line : lines) {
            if (line.startsWith("<?xml version=")) {
                xmlVersionFound = true;
            }
            if (line.contains("<modelVersion>")) {
                modelVersionTagFound = true;
            }
        }
        return xmlVersionFound && modelVersionTagFound;
    }
}

class CreateMavenProjectSetupDirectoryCommandMock extends CreateMavenProjectSetupDirectoryCommand {

    private File homeDir;

    public CreateMavenProjectSetupDirectoryCommandMock(AbstractCommand nextCommand, File homeDir) {
        super(nextCommand);
        this.homeDir = homeDir;
    }

    @Override
    String getHomeDirectroy() {
        return this.homeDir.getAbsolutePath();
    }
}