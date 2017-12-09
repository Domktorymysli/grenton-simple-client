package com.github.domktorymysli.grenton.model;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class Clu {

    private final Integer port;
    private final InetAddress ip;

    public Clu(String ip, Integer port) throws UnknownHostException {
        this.ip = InetAddress.getByName(ip);
        this.port = port;
    }

    public Integer getPort() {
        return port;
    }

    public InetAddress getIp() {
        return ip;
    }
}
