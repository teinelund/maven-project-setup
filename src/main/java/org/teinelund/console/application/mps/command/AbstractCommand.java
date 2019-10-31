package org.teinelund.console.application.mps.command;

import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.Objects;

public abstract class AbstractCommand {

    private AbstractCommand nextCommand;

    public AbstractCommand(AbstractCommand nextCommand) {
        this.nextCommand = nextCommand;
    }

    protected abstract void action(Context context) throws IOException, DocumentException;

    public void executeCommand(Context context) throws IOException, DocumentException {
        action(context);
        if (Objects.nonNull(this.nextCommand)) {
            this.nextCommand.executeCommand(context);
        }
    }
}
