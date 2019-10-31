package org.teinelund.console.application.mps;

import org.dom4j.DocumentException;
import org.teinelund.console.application.mps.argumentparser.ArgumentsVO;
import org.teinelund.console.application.mps.argumentparser.ConsoleArgumentParser;
import org.teinelund.console.application.mps.controller.Controller;
import org.teinelund.console.application.mps.validation.ArgumentValidationException;
import org.teinelund.console.application.mps.validation.ArgumentValidator;

import java.io.IOException;

public class Application
{
    public static void main( String[] args ) throws IOException {
        Application application = new Application(args);
        application.parseCommandLineArguments();
        try {
            application.validateArguments();
            application.executrCommands();
        }
        catch (ArgumentValidationException | DocumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private String[] args;
    private ConsoleArgumentParser argumentParser;
    private ArgumentsVO arguments;

    public Application(String[] args) {
        this.args = args;
    }

    public void parseCommandLineArguments() {
        this.argumentParser = new ConsoleArgumentParser();
        argumentParser.parse(this.args);
        this.arguments = argumentParser.getArguments();
    }

    public void validateArguments() {
        ArgumentValidator validator = new ArgumentValidator();
        validator.validateArguments(this.arguments);
    }

    public void executrCommands() throws IOException, DocumentException {
        Controller controller = new Controller(this.argumentParser, this.arguments);
        controller.selectStrategy();
    }
}
