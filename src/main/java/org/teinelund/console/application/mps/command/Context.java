package org.teinelund.console.application.mps.command;

import org.teinelund.console.application.mps.argumentparser.ArgumentsVO;

import java.nio.file.Path;

public class Context {
    private ArgumentsVO arguments;
    private Path mavenProjectSetupDirectory;
    private Path mavenPomXmlTemplateFile;
    private Path applicationDirectory;

    public ArgumentsVO getArguments() {
        return arguments;
    }

    public void setArguments(ArgumentsVO arguments) {
        this.arguments = arguments;
    }

    public void setMavenProjectSetupDirectoryPath(Path mavenProjectSetupDirectory) {
        this.mavenProjectSetupDirectory = mavenProjectSetupDirectory;
    }

    public Path getMavenProjectSetupDirectory() {
        return this.mavenProjectSetupDirectory;
    }

    public void setMavenPomXmlTemplateFile(Path mavenPomXmlTemplateFile) {
        this.mavenPomXmlTemplateFile = mavenPomXmlTemplateFile;
    }

    public Path getMavenPomXmlTemplateFile() {
        return this.mavenPomXmlTemplateFile;
    }

    public void setApplicationDirectory(Path applicationDirectory) {
        this.applicationDirectory = applicationDirectory;
    }

    public Path getApplicationDirectory() {
        return this.applicationDirectory;
    }
}
