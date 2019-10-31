package org.teinelund.console.application.mps.command;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateApplicationDirectoryCommand extends AbstractCommand {

    public CreateApplicationDirectoryCommand(AbstractCommand nextCommand) {
        super(nextCommand);
    }

    @Override
    protected void action(Context context) throws IOException {
        String applicationName = context.getArguments().getApplicationName();
        if (StringUtils.isBlank(context.getArguments().getApplicationName())) {
            applicationName = context.getArguments().getArtifactId();
        }
        Path applicationDirectory = Paths.get(SystemUtils.USER_DIR, applicationName);
        Files.createDirectory(applicationDirectory);
        context.setApplicationDirectory(applicationDirectory);
    }
}
