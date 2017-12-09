package com.github.domktorymysli.grenton.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public final class Properties {

    private String cluKey;

    private String cluIv;

    private String cluIp;

    private Integer cluPort;

    public String getCluKey() {
        return cluKey;
    }

    public String getCluIv() {
        return cluIv;
    }

    public String getCluIp() {
        return cluIp;
    }

    public void setCluKey(String cluKey) {
        this.cluKey = cluKey;
    }

    public void setCluIv(String cluIv) {
        this.cluIv = cluIv;
    }

    public void setCluIp(String cluIp) {
        this.cluIp = cluIp;
    }

    public void setCluPort(Integer cluPort) {
        this.cluPort = cluPort;
    }

    public Integer getCluPort() {
        return cluPort;
    }

    @Override
    public String toString() {
        return "Properties{" +
                "cluKey='" + cluKey + '\'' +
                ", cluIv='" + cluIv + '\'' +
                ", cluIp='" + cluIp + '\'' +
                ", cluPort=" + cluPort +
                '}';
    }
}
