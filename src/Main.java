import Parser.NBPParser;
import Parser.ServiceParser;
import WebService.AverageGoldPriceInPeriodOfTime;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import WebService.*;




public class Main {

    public static void main(String[] args) throws Exception{

        ServiceParser serviceParser=new ServiceParser();

        serviceParser.parse(args);

        IStrategy strategy=serviceParser.getServiceParser().getStrategy();
        String serviceName=serviceParser.getServiceName();


        WebService webService=WebServiceFactory.create(serviceName,strategy);

        System.out.println(webService.print());

        /*
        URL oracle = new URL("http://api.nbp.pl/api/exchangerates/tables/a/?format=json");
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    */

    }
}
