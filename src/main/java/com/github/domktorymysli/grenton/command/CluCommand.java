package com.github.domktorymysli.grenton.command;

public interface CluCommand {

    String PATTERN = "(req|resp)\\:([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})\\:([a-z0-9]{6,8})\\:(.*)";

    String getCommand();

    String getSessionId();

    String getType();

    String getBody();

    String getIp();

}
