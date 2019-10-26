package org.teinelund.console.application.mps.command;

import java.io.IOException;
import java.util.Objects;

public abstract class AbstractCommand {

    private AbstractCommand nextCommand;

    public AbstractCommand(AbstractCommand nextCommand) {
        this.nextCommand = nextCommand;
    }

    protected abstract void action() throws IOException;

    public void executeCommand() throws IOException {
        action();
        if (Objects.nonNull(this.nextCommand)) {
            this.nextCommand.executeCommand();
        }
    }
}
