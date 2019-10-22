package org.teinelund.console.application.mps.strategy;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.FileReader;
import java.io.IOException;

public class VersionStrategy implements Strategy {

    @Override
    public void execute() {
        try {
            MavenXpp3Reader reader = new MavenXpp3Reader();
            Model model = reader.read(new FileReader("pom.xml"));
            System.out.println("Maven Project Setup. (C) 2019 Henrik Teinelund.");
            System.out.println("Version: " + model.getVersion());
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}
