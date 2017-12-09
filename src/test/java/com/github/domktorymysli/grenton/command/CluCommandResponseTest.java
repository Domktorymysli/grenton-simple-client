package com.github.domktorymysli.grenton.command;

import com.github.domktorymysli.grenton.cipher.model.MessageDecoded;
import junit.framework.Assert;
import junit.framework.TestCase;

public class CluCommandResponseTest extends TestCase {

    public void testGetMessageDecoded() throws Exception {
        MessageDecoded messageDecoded = new MessageDecoded("req:192.168.1.104:001524:test(banan,assasa)");
        CluCommandResponse cluCommandResponse = new CluCommandResponse(messageDecoded);
        Assert.assertEquals("req:192.168.1.104:001524:test(banan,assasa)", cluCommandResponse.getMessageDecoded().toString());
    }

    public void testGetData() throws Exception {
        MessageDecoded messageDecoded = new MessageDecoded("req:192.168.1.104:001524:test('banan','assasa')");
        CluCommandResponse cluCommandResponse = new CluCommandResponse(messageDecoded);

        Assert.assertEquals("test('banan','assasa')", cluCommandResponse.getBody());
        Assert.assertEquals("req", cluCommandResponse.getType());
        Assert.assertEquals("192.168.1.104", cluCommandResponse.getIp());
        Assert.assertEquals("001524", cluCommandResponse.getSessionId());
    }

    public void testStrangeResponse() throws Exception {
        MessageDecoded messageDecoded = new MessageDecoded("some strange string");
        CluCommandResponse cluCommandResponse = new CluCommandResponse(messageDecoded);

        Assert.assertEquals("", cluCommandResponse.getBody());
        Assert.assertEquals("error", cluCommandResponse.getType());
    }

    public void testJsonResponse() throws Exception {
        MessageDecoded messageDecoded = new MessageDecoded("resp:192.168.2.200:00008d39:{\"t1\":23.000000,\"t2\":1.000000}");
        CluCommandResponse cluCommandResponse = new CluCommandResponse(messageDecoded);

        Assert.assertEquals("{\"t1\":23.000000,\"t2\":1.000000}", cluCommandResponse.getBody());
        Assert.assertEquals("resp", cluCommandResponse.getType());
        Assert.assertEquals("192.168.2.200", cluCommandResponse.getIp());
        Assert.assertEquals("00008d39", cluCommandResponse.getSessionId());
    }

    public void testErrorResponse() throws Exception {
        MessageDecoded messageDecoded = new MessageDecoded("resp:192.168.2.200:0000c426:LUA ERROR: a:\\./user.lua:19.000000: attempt to concatenate global 'temperatureSensorTwo' (a function value)");
        CluCommandResponse cluCommandResponse = new CluCommandResponse(messageDecoded);

        Assert.assertEquals("LUA ERROR: a:\\./user.lua:19.000000: attempt to concatenate global 'temperatureSensorTwo' (a function value)", cluCommandResponse.getBody());
        Assert.assertEquals("resp", cluCommandResponse.getType());
        Assert.assertEquals("192.168.2.200", cluCommandResponse.getIp());
        Assert.assertEquals("0000c426", cluCommandResponse.getSessionId());
    }

}
