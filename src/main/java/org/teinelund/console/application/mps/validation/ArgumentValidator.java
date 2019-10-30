package org.teinelund.console.application.mps.validation;

import org.apache.commons.lang3.StringUtils;
import org.teinelund.console.application.mps.argumentparser.ArgumentsVO;

public class ArgumentValidator {
    public void validateArguments(ArgumentsVO arguments) {
        if (arguments.isVersion() || arguments.isHelp()) {
            return;
        }
        if (arguments.isCommandLineApplication() && arguments.isMavenModule()) {
            throw new ArgumentValidationException("Invalid argument. Arguments '--command-line-application' and '--maven-module' may not be used together.");
        }
        if (! arguments.isCommandLineApplication() && ! arguments.isMavenModule()) {
            System.out.println("Warning! Neither arguments '--command-line-application' or '--maven-module' are set. Will create a command line application. See '--help' for more help.");
            arguments.setCreateCommandLineApplication();
        }
        if (StringUtils.isBlank(arguments.getGroupId())) {
            throw new ArgumentValidationException("Invalid argument. Argument '--groupid' is mandatory.");
        }
        if (StringUtils.isBlank(arguments.getArtifactId())) {
            throw new ArgumentValidationException("Invalid argument. Argument '--artifactid' is mandatory.");
        }
    }
}
