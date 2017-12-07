package com.github.domktorymysli.grenton.communication;

import com.github.domktorymysli.grenton.cipher.api.Encoder;
import com.github.domktorymysli.grenton.cipher.model.MessageDecoded;
import com.github.domktorymysli.grenton.cipher.model.MessageEncoded;
import com.github.domktorymysli.grenton.command.CluRawCommand;
import com.github.domktorymysli.grenton.model.Clu;
import junit.framework.TestCase;

import java.net.DatagramSocket;

import static org.mockito.Mockito.*;

public class GrentonApiTest extends TestCase {

    public void testSend() throws Exception {

        Clu clu = new Clu("192.168.2.200", 1234);
        Encoder encoder = mock(Encoder.class);

        when(encoder.encode(any(MessageDecoded.class))).thenReturn(new MessageEncoded("test".getBytes(), 20));
        DatagramSocket datagramSocket = mock(DatagramSocket.class);

        GrentonApi grentonApi = new GrentonApi(clu, encoder,  datagramSocket);
        CluRawCommand cluRawCommand = new CluRawCommand("req:192.168.1.104:001524:test(banan,assasa)");

        grentonApi.send(cluRawCommand);
    }

}