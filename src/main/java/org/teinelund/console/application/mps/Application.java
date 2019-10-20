package org.teinelund.console.application.mps;

import org.teinelund.console.application.mps.argumentparser.ArgumentsVO;
import org.teinelund.console.application.mps.argumentparser.ConsoleArgumentParser;

public class Application
{
    public static void main( String[] args )
    {
        ConsoleArgumentParser argumentParser = new ConsoleArgumentParser();
        argumentParser.parse(args);

        ArgumentsVO arguments = argumentParser.getArguments();
        if (arguments.isHelp()) {
            argumentParser.printHelp();
        }
    }
}
