package com.github.domktorymysli.grenton.communication;

import com.github.domktorymysli.grenton.GrentonCli;
import com.github.domktorymysli.grenton.cipher.api.Encoder;
import com.github.domktorymysli.grenton.cipher.api.exception.GrentonEncoderException;
import com.github.domktorymysli.grenton.cipher.model.MessageDecoded;
import com.github.domktorymysli.grenton.cipher.model.MessageEncoded;
import com.github.domktorymysli.grenton.excpetion.GrentonIoException;
import com.github.domktorymysli.grenton.model.Clu;
import com.github.domktorymysli.grenton.command.CluCommand;
import com.github.domktorymysli.grenton.command.CluCommandResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public final class GrentonApi implements Api {

    private final static Logger logger = Logger.getLogger(GrentonCli.class);

    private final Clu clu;
    private final Encoder encoder;
    private final DatagramSocket socket;

    private int timeout = 2000;
    private int bufforLenght = 2048;

    public GrentonApi(Clu clu, Encoder encoder, DatagramSocket socket) {
        this.clu = clu;
        this.encoder = encoder;
        this.socket = socket;
    }

    public CluCommandResponse send(CluCommand command) throws GrentonIoException, GrentonEncoderException {

        try {
            MessageEncoded messageEncoded = encoder.encode(new MessageDecoded(command.getCommand()));
            byte[] message = messageEncoded.getMsg();
            InetAddress address = this.clu.getIp();
            int port = this.clu.getPort();

            DatagramPacket datagramPacket = new DatagramPacket(message, message.length, address, port);
            DatagramPacket response = new DatagramPacket(new byte[this.bufforLenght], this.bufforLenght);

            logger.info("Sending command: " + command.getCommand() + " to " + this.clu.getIp().getHostName());
            long startTime = System.currentTimeMillis();

            this.socket.setSoTimeout(this.timeout);
            this.socket.send(datagramPacket);
            this.socket.receive(response);
            this.socket.close();
            long estimatedTime = System.currentTimeMillis() - startTime;

            MessageDecoded messageDecoded = encoder.decode(new MessageEncoded(response.getData(), response.getLength()));
            CluCommandResponse cluCommandResponse = new CluCommandResponse(messageDecoded);

            logger.info("Clu response: " + cluCommandResponse.getMessageDecoded().toString() + ", in " + estimatedTime + "ms");

            return cluCommandResponse;

        } catch (IOException e) {

            logger.error("There was error while sending message: " + command.getCommand(), e);

            throw new GrentonIoException(e);
        }
    }
}

