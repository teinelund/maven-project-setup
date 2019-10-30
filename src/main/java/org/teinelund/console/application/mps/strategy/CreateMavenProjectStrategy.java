package org.teinelund.console.application.mps.strategy;

import org.teinelund.console.application.mps.argumentparser.ArgumentsVO;
import org.teinelund.console.application.mps.command.AbstractCommand;
import org.teinelund.console.application.mps.command.Context;
import org.teinelund.console.application.mps.command.CreateDirectroyOfApplicationCommand;

import java.io.IOException;

public class CreateMavenProjectStrategy implements Strategy {

    private ArgumentsVO arguments;
    private AbstractCommand command;

    public CreateMavenProjectStrategy(ArgumentsVO arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() throws IOException {
        wireCommands();
        Context context = new Context();
        context.setArguments(this.arguments);
        command.executeCommand(context);
    }

    void wireCommands() {
        command = new CreateDirectroyOfApplicationCommand(null);
    }
}
