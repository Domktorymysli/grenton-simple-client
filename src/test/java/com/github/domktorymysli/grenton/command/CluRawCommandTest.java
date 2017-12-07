package com.github.domktorymysli.grenton.command;

import junit.framework.TestCase;

public class CluRawCommandTest extends TestCase {

    public void testGetCommand() throws Exception {

        CluRawCommand cluRawCommand = new CluRawCommand("test");
        assertEquals("test", cluRawCommand.getCommand());

    }

}