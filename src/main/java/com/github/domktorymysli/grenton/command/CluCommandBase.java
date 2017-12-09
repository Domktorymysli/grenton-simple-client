package com.github.domktorymysli.grenton.command;

public abstract class CluCommandBase implements CluCommand {

    String type = "error";

    String ip = "";

    String body = "";

    String sessionId = "";

    String command;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getIp() {
        return ip;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

    @Override
    public String getCommand() {
        return command;
    }
}
