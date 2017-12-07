package com.github.domktorymysli.grenton.tools;

import com.github.domktorymysli.grenton.excpetion.PropertiesException;
import com.github.domktorymysli.grenton.model.Properties;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class PropertiesLoaderImpl implements PropertiesLoader {

    public Properties loadProperties(String filename) throws PropertiesException {

        try {
            File file = new File(filename);
            if (!file.canRead()) {
                throw new PropertiesException("Brak pliku properties.xml");
            }

            JAXBContext jaxbContext = JAXBContext.newInstance(Properties.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            return (Properties) jaxbUnmarshaller.unmarshal(file);

        } catch (JAXBException e) {
            throw new PropertiesException(e.getMessage(), e);
        }
    }
}
