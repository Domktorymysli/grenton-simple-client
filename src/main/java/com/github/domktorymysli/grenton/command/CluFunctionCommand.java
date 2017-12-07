package com.github.domktorymysli.grenton.command;

import java.net.InetAddress;
import java.util.Random;

public class CluFunctionCommand implements CluCommand {

    private Random randomGenerator = new Random();

    private final String command;

    private final String sessionId;

    public CluFunctionCommand(InetAddress ip, String functionName, String[] args) {

        this.sessionId = this.generateRandomSessionId();

        String arguments = "nil";

        if (args.length > 0) {
            arguments = String.join(",", args);
        }

        this.command = "req:" + ip.getHostAddress() + ":" + this.sessionId + ":" + functionName + "(" + arguments + ")";
    }

    @Override
    public String getCommand() {
        return this.command;
    }

    @Override
    public String getSessionId() {
        return this.sessionId;
    }

    private String generateRandomSessionId() {

        String randomSessionId;

        for(randomSessionId = Integer.toHexString(randomGenerator.nextInt(65534)); randomSessionId.length() < 6; randomSessionId = "0" + randomSessionId) {

        }

        return randomSessionId;
    }
}
