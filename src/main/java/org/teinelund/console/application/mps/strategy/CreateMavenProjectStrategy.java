package org.teinelund.console.application.mps.strategy;

import org.teinelund.console.application.mps.argumentparser.ArgumentsVO;

public class CreateMavenProjectStrategy implements Strategy {

    private ArgumentsVO arguments;

    public CreateMavenProjectStrategy(ArgumentsVO arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() {

    }
}
