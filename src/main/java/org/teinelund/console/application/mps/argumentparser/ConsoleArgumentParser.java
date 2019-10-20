package org.teinelund.console.application.mps.argumentparser;

import com.beust.jcommander.JCommander;

public class ConsoleArgumentParser {

    private ArgumentsVO arguments;
    private JCommander jCommander;

    public void parse(String[] args) {
        this.arguments = new ArgumentsVO();
        this.jCommander = JCommander.newBuilder()
                .addObject(this.arguments)
                .build();
                this.jCommander.parse(args);
    }

    public ArgumentsVO getArguments() {
        return this.arguments;
    }

    public void printHelp() {
        this.jCommander.usage();
    }
}
