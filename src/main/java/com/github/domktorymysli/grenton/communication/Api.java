package com.github.domktorymysli.grenton.communication;

import com.github.domktorymysli.grenton.cipher.api.exception.GrentonEncoderException;
import com.github.domktorymysli.grenton.command.CluCommand;
import com.github.domktorymysli.grenton.excpetion.GrentonIoException;
import com.github.domktorymysli.grenton.model.CluResponse;

public interface Api {

    CluResponse send(CluCommand command) throws GrentonIoException, GrentonEncoderException;

}
