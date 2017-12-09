package com.github.domktorymysli.grenton.command;

import java.net.InetAddress;
import java.util.Random;

final public class CluFunctionCommand extends CluCommandBase implements CluCommand {

    private Random randomGenerator = new Random();

    public CluFunctionCommand(InetAddress ip, String functionName, String[] args) {

        this.sessionId = this.generateRandomSessionId();

        String arguments = "nil";

        if (args.length > 0) {
            arguments = String.join(",", args);
        }

        this.command = "req:" + ip.getHostAddress() + ":" + this.sessionId + ":" + functionName + "(" + arguments + ")";
    }

    private String generateRandomSessionId() {

        String randomSessionId;

        for(randomSessionId = Integer.toHexString(randomGenerator.nextInt(65534)); randomSessionId.length() < 6; randomSessionId = "0" + randomSessionId) {

        }

        return randomSessionId;
    }
}
