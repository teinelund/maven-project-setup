package org.teinelund.console.application.mps.strategy;

import org.dom4j.DocumentException;

import java.io.IOException;

public interface Strategy {
    public void execute() throws IOException, DocumentException;
}
