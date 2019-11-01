package org.teinelund.console.application.mps.command;

import org.dom4j.DocumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.teinelund.console.application.mps.argumentparser.ArgumentsVO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class CreateApplicationDirectoryCommandTest {

    private Context context = null;
    private final String APPLICATION_NAME = "APPLICATION_NAME";

    @TempDir
    File currentDir;

    @BeforeEach
    void initTest() {
        this.context = new Context();
        ArgumentsVO arguments = new ArgumentsVO.Builder().setApplicationName(APPLICATION_NAME).build();
        this.context.setArguments(arguments);
    }

    @Test
    void actionWhereApplicationDirectoryExist() throws IOException, DocumentException {
        // Initialize
        createFilesAndFolders();
        AbstractCommand sut = new CreateApplicationDirectoryCommand(null);
        assertThat(doesApplicationDirectoryExist()).isTrue();
        // Test
        sut.action(context);
        // Verify
        assertThat(doesApplicationDirectoryExist()).isTrue();
    }

    @Test
    void actionWhereApplicationDirectoryDoesNotExist() throws IOException, DocumentException {
        // Initialize
        AbstractCommand sut = new CreateApplicationDirectoryCommandMock(null, this.currentDir);
        assertThat(doesApplicationDirectoryExist()).isFalse();
        // Test
        sut.action(context);
        // Verify
        assertThat(doesApplicationDirectoryExist()).isTrue();
    }

    void createFilesAndFolders() throws IOException {
        Path applicationDirectory = Paths.get(this.currentDir.getAbsolutePath(), APPLICATION_NAME);
        Files.createDirectory(applicationDirectory);
    }

    boolean doesApplicationDirectoryExist() {
        Path mavenProjectSetupDirectory = Paths.get(this.currentDir.getAbsolutePath(), APPLICATION_NAME);
        return Files.exists(mavenProjectSetupDirectory);
    }

}

class CreateApplicationDirectoryCommandMock extends CreateApplicationDirectoryCommand {

    private File homeDir;

    public CreateApplicationDirectoryCommandMock(AbstractCommand nextCommand, File homeDir) {
        super(nextCommand);
        this.homeDir = homeDir;
    }

    @Override
    String getCurrentDirectory() {
        return this.homeDir.getAbsolutePath();
    }
}