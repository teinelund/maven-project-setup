package org.teinelund.console.application.mps.controller;

import org.teinelund.console.application.mps.argumentparser.ArgumentsVO;
import org.teinelund.console.application.mps.argumentparser.ConsoleArgumentParser;
import org.teinelund.console.application.mps.strategy.CreateMavenProjectStrategy;
import org.teinelund.console.application.mps.strategy.HelpStrategy;
import org.teinelund.console.application.mps.strategy.Strategy;
import org.teinelund.console.application.mps.strategy.VersionStrategy;

public class Controller {

    private ConsoleArgumentParser argumentParser;
    private ArgumentsVO arguments;

    public Controller(ConsoleArgumentParser argumentParser, ArgumentsVO arguments) {
        this.argumentParser = argumentParser;
        this.arguments = arguments;
    }

    public void selectStrategy() {
        Strategy strategy = null;
        if (this.arguments.isHelp()) {
            strategy = new HelpStrategy(this.argumentParser);
        }
        else if (this.arguments.isVersion()) {
            strategy = new VersionStrategy();
        }
        else {
            strategy = new CreateMavenProjectStrategy(this.arguments);
        }
        strategy.execute();
    }
}
