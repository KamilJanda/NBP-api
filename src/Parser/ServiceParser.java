package Parser;

import java.io.IOException;

public class ServiceParser {

    private String serviceName;
    private Parser serviceParser;
    private ParserCreator parserCreator;

    public ServiceParser()
    {
        parserCreator= new ParserCreator();
    }


    public void parse(String[] args){

        String functions="GoldAndCurrencyPriceForDate\nAverageGoldPriceInPeriodOfTime\nCurrencyWithTheBiggestAmplitude\nCurrencyWithTheLowestValue\nCurrencyExchangeRateSorted\nCurrencyTheHighestAndTheLowestRate\nCurrencyRateTable";

        if(args.length==0)
        {
            throw new IllegalArgumentException("Format of query get from {service} {function} {arguments for function}\n"+"Functios:\n"+functions);
        }

        if(args[0].toLowerCase().equals("get")&&args[1].toLowerCase().equals("from"))
        {
            serviceName =args[2];
        }
        else
        {
            throw new IllegalArgumentException();
        }

        serviceParser=parserCreator.create(serviceName);
        serviceParser.parse(args);
    }

    public String getServiceName() {
        return serviceName;
    }

    public Parser getServiceParser() {
        return serviceParser;
    }


}
