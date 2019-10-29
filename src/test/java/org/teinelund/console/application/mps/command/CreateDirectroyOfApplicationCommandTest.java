package org.teinelund.console.application.mps.command;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class CreateDirectroyOfApplicationCommandTest {

    @TempDir
    File homeDir;

    @Test
    void actionWhereDirectoryOfApplicationExist() throws IOException {
        // Initialize
        createFilesAndFolders();
        CreateDirectroyOfApplicationCommand sut = new CreateDirectroyOfApplicationCommandMock(null, this.homeDir);
        assertThat(doesTheDirectoryMavenProjectSetupExist()).isTrue();
        // Test
        sut.action();
        // Verify
        assertThat(doesTheDirectoryMavenProjectSetupExist()).isTrue();
    }

    @Test
    void actionWhereDirectoryOfApplicationDoesNotExist() throws IOException {
        // Initialize
        CreateDirectroyOfApplicationCommand sut = new CreateDirectroyOfApplicationCommandMock(null, this.homeDir);
        assertThat(doesTheDirectoryMavenProjectSetupExist()).isFalse();
        // Test
        sut.action();
        // Verify
        assertThat(doesTheDirectoryMavenProjectSetupExist()).isTrue();
    }

    void createFilesAndFolders() throws IOException {
        Path mavenProjectSetupDirectory = Paths.get(this.homeDir.getAbsolutePath(), ".maven-project-setup");
        Files.createDirectory(mavenProjectSetupDirectory);
    }


    boolean doesTheDirectoryMavenProjectSetupExist() {
        Path mavenProjectSetupDirectory = Paths.get(this.homeDir.getAbsolutePath(), ".maven-project-setup");
        return Files.exists(mavenProjectSetupDirectory);
    }
}

class CreateDirectroyOfApplicationCommandMock extends CreateDirectroyOfApplicationCommand {

    private File homeDir;

    public CreateDirectroyOfApplicationCommandMock(AbstractCommand nextCommand, File homeDir) {
        super(nextCommand);
        this.homeDir = homeDir;
    }

    @Override
    String getHomeDirectroy() {
        return this.homeDir.getAbsolutePath();
    }
}