package com.github.domktorymysli.grenton.command;

import com.github.domktorymysli.grenton.cipher.model.MessageDecoded;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

final public class CluCommandResponse extends CluCommandBase implements CluCommand {

    private final MessageDecoded messageDecoded;

    public CluCommandResponse(MessageDecoded messageDecoded) {

        this.messageDecoded = messageDecoded;

        Pattern p = Pattern.compile(CluCommand.PATTERN);
        Matcher m = p.matcher(messageDecoded.toString());

        if (m.matches()) {
            this.type = m.group(1);
            this.ip = m.group(2);
            this.sessionId = m.group(3);
            this.body = m.group(4);
        }
    }

    public String getCommand() {
        return this.messageDecoded.toString();
    }

    public MessageDecoded getMessageDecoded() {
        return messageDecoded;
    }

    @Override
    public String toString() {
        return this.messageDecoded.toString();
    }

}
