package org.teinelund.console.application.mps.strategy;

import org.teinelund.console.application.mps.argumentparser.ConsoleArgumentParser;

public class HelpStrategy implements Strategy {

    private ConsoleArgumentParser argumentParser;

    public HelpStrategy(ConsoleArgumentParser argumentParser) {
        this.argumentParser = argumentParser;
    }

    @Override
    public void execute() {
        this.argumentParser.printHelp();
    }
}
