package org.teinelund.console.application.mps.argumentparser;

import com.beust.jcommander.Parameter;

public class ArgumentsVO {

    @Parameter(names = { "-h", "--help" }, description = "Display this help page.", help = true, order = 7)
    private boolean isHelp;

    @Parameter(names = { "-v", "--version" }, description = "Show version.", order = 8)
    private boolean isVersion;

    @Parameter(names = { "-c", "--command-line-application" }, description = "Create a Command Line Application. This is the default.", order = 0)
    private boolean isCommandLineApplication;

    @Parameter(names = { "-m", "--maven-module" }, description = "Create a Maven module.", order = 1)
    private boolean isMavenModule;

    @Parameter(names = { "-g", "--groupid" }, description = "Set groupId.", order = 2)
    private String groupId;

    @Parameter(names = { "-a", "--artifactid" }, description = "Set artifactId.", order = 3)
    private String artifactId;

    @Parameter(names = { "-p", "--package" }, description = "Set package.", order = 4)
    private String packageName;

    @Parameter(names = { "-n", "--applicationName" }, description = "Set Application Name.", order = 5)
    private String applicationName;

    @Parameter(names = { "-d", "--description" }, description = "Set Application description.", order = 6)
    private String description;

    public ArgumentsVO() {}

    ArgumentsVO( Builder builder ) {
        this.isHelp = builder.isHelp;
        this.isVersion = builder.isVersion;
        this.isCommandLineApplication = builder.isCommandLineApplication;
        this.isMavenModule = builder.isMavenModule;
        this.groupId = builder.groupId;
        this.artifactId = builder.artifactId;
        this.packageName = builder.packageName;
        this.applicationName = builder.applicationName;
        this.description = builder.description;
    }

    public boolean isHelp() {
        return this.isHelp;
    }

    public boolean isVersion() {
        return this.isVersion;
    }

    public boolean isCommandLineApplication() {
        return this.isCommandLineApplication;
    }

    public void setCreateCommandLineApplication() {
        this.isCommandLineApplication = true;
    }

    public boolean isMavenModule() {
        return this.isMavenModule;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public String getArtifactId() {
        return this.artifactId;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getApplicationName() {
        return this.applicationName;
    }

    public String getDescription() {
        return this.description;
    }

    public static class Builder {
        private boolean isHelp;
        private boolean isVersion;
        private boolean isCommandLineApplication;
        private boolean isMavenModule;
        private String groupId;
        private String artifactId;
        private String packageName;
        private String applicationName;
        private String description;

        public Builder setCommandLineApplication(boolean isCommandLineApplication) {
            this.isCommandLineApplication = isCommandLineApplication;
            return this;
        }

        public Builder setMavenModule(boolean isMavenModule) {
            this.isMavenModule = isMavenModule;
            return this;
        }

        public Builder setGroupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public Builder setArtifactId(String artifactId) {
            this.artifactId = artifactId;
            return this;
        }

        public Builder setPackageName(String packageName) {
            this.packageName = packageName;
            return this;
        }

        public Builder setApplicationName(String applicationName) {
            this.applicationName = applicationName;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setIsHelp( boolean isHelp ) {
            this.isHelp = isHelp;
            return this;
        }

        public Builder setIsVersion( boolean isVersion ) {
            this.isVersion = isVersion;
            return this;
        }

        public ArgumentsVO build() {
            return new ArgumentsVO( this );
        }
    }
}
