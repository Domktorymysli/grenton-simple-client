package com.github.domktorymysli.grenton.excpetion;

public final class PropertiesException extends Exception {

    public PropertiesException(String messsage, Throwable e)
    {
        super(messsage, e);
    }

    public PropertiesException(String message) {
        super(message);
    }
}
