package com.github.domktorymysli.grenton.simple_client.excpetion;

import java.io.IOException;

public class GrentonIoException extends IOException {


    public GrentonIoException(Throwable cause) {
        super(cause);
    }

    public GrentonIoException(String message, Throwable cause) {
        super(message, cause);
    }
}
