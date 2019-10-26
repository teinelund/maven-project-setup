package org.teinelund.console.application.mps.argumentparser;

import com.beust.jcommander.Parameter;

public class ArgumentsVO {

    @Parameter(names = { "-h", "--help" }, description = "Display this help page.")
    private boolean isHelp;

    @Parameter(names = { "-v", "--version" }, description = "Show version.")
    private boolean isVersion;

    @Parameter(names = { "-c", "--command-line-application" }, description = "Create a Command Line Application. This is the default.")
    private boolean isCommandLineApplication;

    @Parameter(names = { "-m", "--maven-module" }, description = "Create a Maven module.")
    private boolean isMavenModule;

    @Parameter(names = { "-g", "--groupid" }, description = "Set groupId.")
    private String groupId;

    @Parameter(names = { "-a", "--artifactid" }, description = "Set artifactId.")
    private String artifactId;

    @Parameter(names = { "-p", "--package" }, description = "Set package.")
    private boolean packageName;

    @Parameter(names = { "-n", "--applicationName" }, description = "Set Application Name.")
    private boolean applicationName;

    @Parameter(names = { "-d", "--description" }, description = "Set Application description.")
    private boolean description;

    public boolean isHelp() {
        return this.isHelp;
    }

    public boolean isVersion() {
        return this.isVersion;
    }
}
