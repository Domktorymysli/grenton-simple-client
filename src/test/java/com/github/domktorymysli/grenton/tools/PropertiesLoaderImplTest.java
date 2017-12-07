package com.github.domktorymysli.grenton.tools;

import com.github.domktorymysli.grenton.excpetion.PropertiesException;
import com.github.domktorymysli.grenton.model.Properties;
import junit.framework.Assert;
import junit.framework.TestCase;

public class PropertiesLoaderImplTest extends TestCase {

    public void testLoadProperties() throws Exception {

        PropertiesLoader propertiesLoader = new PropertiesLoaderImpl();
        Properties properties = propertiesLoader.loadProperties("properties-dist.xml");
        Assert.assertEquals("192.168.2.200", properties.getCluIp());
        Assert.assertEquals(1234, (int) properties.getCluPort());
        Assert.assertEquals("KY1Ajg+pDBQcP2cHnIFNRQ==", properties.getCluKey());
        Assert.assertEquals("/gV+nXMOUlBbuc3uhkk/eA==", properties.getCluIv());

    }

    public void testLoadPropertiesForNotExistingFile() throws Exception {

        try {
            PropertiesLoader propertiesLoader = new PropertiesLoaderImpl();
            Properties properties = propertiesLoader.loadProperties("nofile.xml");
            fail("expected exception was not occurred.");
        } catch(PropertiesException e) {
            // ok
        }

    }

}