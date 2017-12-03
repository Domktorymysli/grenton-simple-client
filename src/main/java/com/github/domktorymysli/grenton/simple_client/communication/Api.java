package com.github.domktorymysli.grenton.simple_client.communication;

import com.github.domktorymysli.grenton.cipher.api.Encoder;
import com.github.domktorymysli.grenton.cipher.api.exception.GrentonEncoderException;
import com.github.domktorymysli.grenton.cipher.model.MessageDecoded;
import com.github.domktorymysli.grenton.cipher.model.MessageEncoded;
import com.github.domktorymysli.grenton.simple_client.command.CluCommand;
import com.github.domktorymysli.grenton.simple_client.excpetion.GrentonIoException;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Api {

    private final Clu clu;
    private final Encoder encoder;

    public Api(Clu clu, Encoder encoder) {
        this.clu = clu;
        this.encoder = encoder;
    }

    public void send(CluCommand command) throws GrentonIoException, GrentonEncoderException {

        try {
            InetAddress address = this.clu.getIp();
            int port = this.clu.getPort();

            MessageEncoded messageEncoded = encoder.encode(new MessageDecoded(command.getCommand()));
            byte[] message = messageEncoded.getMsg();

            DatagramSocket socket = new DatagramSocket();
            DatagramPacket datagramPacket = new DatagramPacket(message, message.length, address, port);

            socket.send(datagramPacket);

        } catch (IOException e) {
            throw new GrentonIoException(e);
        }
    }
}

