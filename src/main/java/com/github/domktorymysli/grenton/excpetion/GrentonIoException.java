package com.github.domktorymysli.grenton.excpetion;

import java.io.IOException;

public final class GrentonIoException extends IOException {

    public GrentonIoException(Throwable cause) {
        super(cause);
    }

    public GrentonIoException(String message, Throwable cause) {
        super(message, cause);
    }

}
