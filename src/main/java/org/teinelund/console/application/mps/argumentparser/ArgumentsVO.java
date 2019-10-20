package org.teinelund.console.application.mps.argumentparser;

import com.beust.jcommander.Parameter;

public class ArgumentsVO {

    @Parameter(names = { "-h", "--help" }, description = "Display this help page.")
    private boolean isHelp;

    @Parameter(names = { "-v", "--version" }, description = "Show version.")
    private boolean isVersion;

    public boolean isHelp() {
        return this.isHelp;
    }

    public boolean isVersion() {
        return this.isVersion;
    }
}
