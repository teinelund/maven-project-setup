package org.teinelund.console.application.mps.command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class CreateDirectroyOfApplicationCommand extends AbstractCommand {

    public CreateDirectroyOfApplicationCommand(AbstractCommand nextCommand) {
        super(nextCommand);
    }

    @Override
    protected void action() throws IOException {
        Path mavenProjectSetupDirectory = Paths.get(getHomeDirectroy(), ".maven-project-setup");
        if (Files.notExists(mavenProjectSetupDirectory)) {
            Files.createDirectory(mavenProjectSetupDirectory);
        }
    }

    String getHomeDirectroy() {
        return System.getProperty("user.home");
    }
}
