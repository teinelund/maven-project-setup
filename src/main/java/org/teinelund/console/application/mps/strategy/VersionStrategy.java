package org.teinelund.console.application.mps.strategy;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class VersionStrategy implements Strategy {

    @Override
    public void execute() {
        try {
            final Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("Application.properties"));
            System.out.println("Maven Project Setup. (C) 2019 Henrik Teinelund.");
            System.out.println("Version: " + properties.getProperty("application.version"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
