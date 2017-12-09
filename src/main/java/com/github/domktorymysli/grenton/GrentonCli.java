package com.github.domktorymysli.grenton;

import com.github.domktorymysli.grenton.cipher.api.Encoder;
import com.github.domktorymysli.grenton.cipher.api.EncoderGrenton;
import com.github.domktorymysli.grenton.command.CluCommand;
import com.github.domktorymysli.grenton.command.CluFunctionCommand;
import com.github.domktorymysli.grenton.communication.Api;
import com.github.domktorymysli.grenton.communication.GrentonApi;
import com.github.domktorymysli.grenton.excpetion.PropertiesException;
import com.github.domktorymysli.grenton.model.Clu;
import com.github.domktorymysli.grenton.command.CluCommandResponse;
import com.github.domktorymysli.grenton.model.Properties;
import com.github.domktorymysli.grenton.tools.PropertiesLoader;
import com.github.domktorymysli.grenton.tools.PropertiesLoaderImpl;
import org.apache.commons.cli.*;
import org.apache.log4j.Logger;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public final class GrentonCli
{
    private final static Logger logger = Logger.getLogger(GrentonCli.class);

    private static Options getCliOptions()
    {
        Options options = new Options();
        options.addRequiredOption( "c", "config", true, "plik konfiguracyjny. Zobacz przykladowy plik properties-dist.xml");
        options.addRequiredOption("f", "function", true, "nazwa funkcji na CLU" );
        options.addRequiredOption("ip", "ip", true, "ip zwrotne");
        options.addOption(OptionBuilder.withValueSeparator().hasArg().withLongOpt("parameters").withDescription("parametry funkcji, oddzielone przecinkami").create("p"));

        return options;
    }

    private static Properties getProperties(String file) throws PropertiesException {
        PropertiesLoader propertiesLoader = new PropertiesLoaderImpl();

        return propertiesLoader.loadProperties(file);
    }

    public static void main( String[] args ) throws SocketException {

        CommandLineParser parser = new DefaultParser();
        Options options = GrentonCli.getCliOptions();
        CommandLine cmd = null;
        Properties properties = null;

        try {
            cmd = parser.parse(options, args, true);
        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("grentonCli", options);
            System.out.println("");
            System.out.println("Parse error: " + e.getMessage());
            System.out.println("");

            System.exit(0);
        }

        try {
            String filename = cmd.getOptionValue("c");
            properties = GrentonCli.getProperties(filename);
        } catch (PropertiesException e) {
            System.out.println("Plik nie został znaleziony!");
            System.exit(1);
        }

        try {
            Clu clu = new Clu(properties.getCluIp(), properties.getCluPort());
            Encoder encoder = new EncoderGrenton(properties.getCluKey(), properties.getCluIv());
            String ip = cmd.getOptionValue("ip");
            String functionName = cmd.getOptionValue("f");
            String[] parameters = cmd.getOptionValues("p") == null ? new String[]{} : cmd.getOptionValues("p");

            DatagramSocket socket = new DatagramSocket();
            Api api = new GrentonApi(clu, encoder, socket);

            CluCommand command = new CluFunctionCommand(InetAddress.getByName(ip), functionName, parameters);

            CluCommandResponse result = api.send(command);
            System.out.println(result.getBody());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage(), e);
        }
    }
}
