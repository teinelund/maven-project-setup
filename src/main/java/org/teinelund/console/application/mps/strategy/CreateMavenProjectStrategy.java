package org.teinelund.console.application.mps.strategy;

import org.dom4j.DocumentException;
import org.teinelund.console.application.mps.argumentparser.ArgumentsVO;
import org.teinelund.console.application.mps.command.AbstractCommand;
import org.teinelund.console.application.mps.command.Context;
import org.teinelund.console.application.mps.command.CreateMavenProjectSetupDirectoryCommand;
import org.teinelund.console.application.mps.command.CreateApplicationDirectoryCommand;
import org.teinelund.console.application.mps.command.CreatePomXmlFileCommand;

import java.io.IOException;

public class CreateMavenProjectStrategy implements Strategy {

    private ArgumentsVO arguments;
    private AbstractCommand command;

    public CreateMavenProjectStrategy(ArgumentsVO arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() throws IOException, DocumentException {
        wireCommands();
        Context context = new Context();
        context.setArguments(this.arguments);
        this.command.executeCommand(context);
    }

    void wireCommands() {
        AbstractCommand cpxfc = new CreatePomXmlFileCommand(null);
        AbstractCommand cadc = new CreateApplicationDirectoryCommand(cpxfc);
        this.command = new CreateMavenProjectSetupDirectoryCommand(cadc);
    }
}
