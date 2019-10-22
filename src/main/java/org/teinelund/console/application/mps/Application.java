package org.teinelund.console.application.mps;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.teinelund.console.application.mps.argumentparser.ArgumentsVO;
import org.teinelund.console.application.mps.argumentparser.ConsoleArgumentParser;
import org.teinelund.console.application.mps.controller.Controller;
import org.teinelund.console.application.mps.validation.ArgumentValidator;

import java.io.FileReader;
import java.io.IOException;

public class Application
{
    public static void main( String[] args ) throws IOException, XmlPullParserException {
        Application application = new Application(args);
        application.parseCommandLineArguments();
        application.validateArguments();
        application.executrCommands();
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

    public void executrCommands() throws IOException, XmlPullParserException {
        Controller controller = new Controller(this.argumentParser, this.arguments);
        controller.selectStrategy();
    }
}
