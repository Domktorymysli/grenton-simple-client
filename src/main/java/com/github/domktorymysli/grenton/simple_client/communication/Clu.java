package com.github.domktorymysli.grenton.simple_client.communication;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Clu {

    private final int port;
    private final InetAddress ip;

    public Clu(String ip, int port) throws UnknownHostException {
        this.ip = InetAddress.getByName(ip);
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public InetAddress getIp() {
        return ip;
    }
}
