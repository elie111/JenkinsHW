package com.coderfromscratch.httpserver;

import com.coderfromscratch.httpserver.config.Configuration;
import com.coderfromscratch.httpserver.config.ConfigurationManager;
import com.coderfromscratch.httpserver.core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.ObjectInstance;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * Driver Class for the Http Server
 *
 */
public class HttpServer {
    public static Path temp;

    static {
        try {
            temp = Files.createTempFile("result", ".html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

    public HttpServer() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.ynet.co.il/Integration/StoryRss2.xml");
        BufferedReader read = new BufferedReader(
                new InputStreamReader(url.openStream()));



        Source xml = new StreamSource(read);
        Source xslt = new StreamSource(HttpServer.class.getResourceAsStream("/product.xsl"));
        StringWriter sw = new StringWriter();

        Files.deleteIfExists(temp);
        System.out.println("Temp file : " + temp);
        try {


            FileWriter fw = new FileWriter(String.valueOf(temp));
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer trasform = tFactory.newTransformer(xslt);
            trasform.transform(xml, new StreamResult(sw));
            fw.write(sw.toString());
            fw.close();
//            String tempDir = System.getProperty("java.io.tmpdir");
//
//            System.out.println("Temporary file will be stored here : "+tempDir);


            System.out.println("product.html generated successfully at D:\\template ");


        } catch (IOException | TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
//

//        File htmlFile = new File(String.valueOf(temp));
//
//
//        Desktop.getDesktop().open(htmlFile);
//        LOGGER.info("Server starting...");
        Path temp2=Files.createTempFile("http", ".json");
        Files.deleteIfExists(temp2);


        FileWriter fw2 = new FileWriter(String.valueOf(temp2));
        fw2.write("{\"port\": 8080, \"webroot\": \"/tmp\"}");
        fw2.close();

        ConfigurationManager a;
        a=ConfigurationManager.getInstance();
        System.out.println(a);
        a.loadConfigurationFile(temp2.toString());
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
System.out.println("Using Port: " + conf.getPort());
conf.setWebroot("localhost");
        System.out.println("Using WebRoot: " + conf.getWebroot());
//        LOGGER.info("Using Port: " + conf.getPort());
//        LOGGER.info("Using WebRoot: " + conf.getWebroot());

        try {
            ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot());
            serverListenerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
            // TODO handle later.
        }


    }

}
