package org.teinelund.console.application.mps.validation;

import org.teinelund.console.application.mps.argumentparser.ArgumentsVO;

public class ArgumentValidator {
    public void validateArguments(ArgumentsVO arguments) {
        if (arguments.isVersion() || arguments.isHelp()) {
            return;
        }
    }
}
