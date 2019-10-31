package org.teinelund.console.application.mps.command;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class CreateMavenProjectSetupDirectoryCommand extends AbstractCommand {

    public CreateMavenProjectSetupDirectoryCommand(AbstractCommand nextCommand) {
        super(nextCommand);
    }

    @Override
    protected void action(Context context) throws IOException {
        Path mavenProjectSetupDirectory = Paths.get(getHomeDirectroy(), ".maven-project-setup");
        if (Files.notExists(mavenProjectSetupDirectory)) {
            Files.createDirectory(mavenProjectSetupDirectory);
        }
        context.setMavenProjectSetupDirectoryPath(mavenProjectSetupDirectory);
        Path mavenPomXmlTemplateFile = Paths.get(mavenProjectSetupDirectory.toAbsolutePath().toString(), "pom-template.xml");
        if (Files.notExists(mavenPomXmlTemplateFile)) {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("pom-template.xml");
            Files.copy(inputStream, mavenPomXmlTemplateFile);
        }
        context.setMavenPomXmlTemplateFile(mavenPomXmlTemplateFile);
    }

    String getHomeDirectroy() {
        return System.getProperty("user.home");
    }
}
