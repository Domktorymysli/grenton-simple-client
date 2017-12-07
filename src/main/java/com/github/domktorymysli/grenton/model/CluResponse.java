package com.github.domktorymysli.grenton.model;

import com.github.domktorymysli.grenton.cipher.model.MessageDecoded;

public class CluResponse {

    private final MessageDecoded messageDecoded;

    public CluResponse(MessageDecoded messageDecoded) {
        this.messageDecoded = messageDecoded;
    }

    public MessageDecoded getMessageDecoded() {
        return messageDecoded;
    }

    @Override
    public String toString() {
        return this.messageDecoded.toString();
    }
}
