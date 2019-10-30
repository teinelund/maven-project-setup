package org.teinelund.console.application.mps.command;

import org.teinelund.console.application.mps.argumentparser.ArgumentsVO;

public class Context {
    private ArgumentsVO arguments;

    public ArgumentsVO getArguments() {
        return arguments;
    }

    public void setArguments(ArgumentsVO arguments) {
        this.arguments = arguments;
    }
}
