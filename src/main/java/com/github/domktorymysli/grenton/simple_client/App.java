package com.github.domktorymysli.grenton.simple_client;

import com.github.domktorymysli.grenton.cipher.api.Encoder;
import com.github.domktorymysli.grenton.cipher.api.EncoderGrenton;
import com.github.domktorymysli.grenton.cipher.api.exception.GrentonEncoderException;
import com.github.domktorymysli.grenton.cipher.model.MessageDecoded;
import com.github.domktorymysli.grenton.cipher.model.MessageEncoded;
import com.github.domktorymysli.grenton.simple_client.command.CluCommand;
import com.github.domktorymysli.grenton.simple_client.communication.Api;
import com.github.domktorymysli.grenton.simple_client.communication.Clu;
import com.github.domktorymysli.grenton.simple_client.excpetion.GrentonIoException;

import java.net.UnknownHostException;

public class App 
{
    public static void main( String[] args ) {
        try {
            Clu clu = new Clu("192.168.2.200", 1234);
            Encoder encoder = new EncoderGrenton("KY1Ajg+pDBQcP2cHnIFNRQ==", "/gV+nXMOUlBbuc3uhkk/eA==");
            Api api = new Api(clu, encoder);

            CluCommand command = new CluCommand("req:local_ip:000001:DOUT_8565:execute(0, 1)");

            System.out.println("Trying to send message.");
            api.send(command);
            System.out.println("Message was sent.");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (GrentonIoException e) {
            e.printStackTrace();
        } catch (GrentonEncoderException e) {
            e.printStackTrace();
        }
    }
}
