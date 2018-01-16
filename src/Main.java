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


        try {
            serviceParser.parse(args);

            IStrategy strategy=serviceParser.getServiceParser().getStrategy();
            String serviceName=serviceParser.getServiceName();


            WebService webService=WebServiceFactory.create(serviceName,strategy);

            System.out.println(webService.print());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }







    }
}
