package org.teinelund.console.application.mps;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.teinelund.console.application.mps.argumentparser.ArgumentsVO;
import org.teinelund.console.application.mps.argumentparser.ConsoleArgumentParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Application
{
    public static void main( String[] args ) throws IOException, XmlPullParserException {
        ConsoleArgumentParser argumentParser = new ConsoleArgumentParser();
        argumentParser.parse(args);

        ArgumentsVO arguments = argumentParser.getArguments();
        if (arguments.isHelp()) {
            argumentParser.printHelp();
        }
        if (arguments.isVersion()) {
            MavenXpp3Reader reader = new MavenXpp3Reader();
            Model model = reader.read(new FileReader("pom.xml"));
            System.out.println("Maven Project Setup. (C) 2019 Henrik Teinelund.");
            System.out.println("Version: " + model.getVersion());
        }
    }
}
