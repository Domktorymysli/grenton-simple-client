package com.github.domktorymysli.grenton.tools;

import com.github.domktorymysli.grenton.excpetion.PropertiesException;
import com.github.domktorymysli.grenton.model.Properties;

public interface PropertiesLoader {

    Properties loadProperties(String filename) throws PropertiesException;

}
