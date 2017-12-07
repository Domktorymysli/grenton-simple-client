package com.github.domktorymysli.grenton.command;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.net.InetAddress;

public class CluFunctionCommandTest extends TestCase {

    public void testGetCommand() throws Exception {

        InetAddress ip = InetAddress.getByName("127.0.0.1");

        CluFunctionCommand cluFunctionCommand = new CluFunctionCommand(ip, "test", new String[]{"arg1", "arg2"});

        Assert.assertTrue(cluFunctionCommand.getCommand(), cluFunctionCommand.getCommand().matches("req:127\\.0\\.0\\.1:[a-f0-9]{6}:test\\(arg1,arg2\\)"));

    }

    public void testGetCommandWithStringParams() throws Exception {

        InetAddress ip = InetAddress.getByName("127.0.0.1");

        CluFunctionCommand cluFunctionCommand = new CluFunctionCommand(ip, "test", new String[]{"\'arg1\'", "\'arg2\'"});

        Assert.assertTrue(cluFunctionCommand.getCommand(), cluFunctionCommand.getCommand().matches("req:127\\.0\\.0\\.1:[a-f0-9]{6}:test\\(\\\'arg1\\\',\\\'arg2\\\'\\)"));

    }

    public void testGetCommandWithoutParams() throws Exception {

        InetAddress ip = InetAddress.getByName("127.0.0.1");

        CluFunctionCommand cluFunctionCommand = new CluFunctionCommand(ip, "test", new String[]{});

        Assert.assertTrue(cluFunctionCommand.getCommand(), cluFunctionCommand.getCommand().matches("req:127\\.0\\.0\\.1:[a-f0-9]{6}:test\\(nil\\)"));

    }

}