package com.github.domktorymysli.grenton.command;

final public class CluRawCommand extends CluCommandBase implements CluCommand {

    private final String command;

    public CluRawCommand(String command) {
        this.command = command;
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public String getSessionId() {
        return "000001";
    }
}
