package Parser;

import WebService.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NBPParserTest {


    @Test
    void parse()
    {
        NBPParser nbpParser=new NBPParser();
        IStrategy strategy=null;


        String[] args1={"get","from","NBP","GoldAndCurrencyPriceForDate","-c","PLN","-d","2016-01-01"};
        nbpParser.parse(args1);
        strategy=nbpParser.getStrategy();

        assertTrue(strategy instanceof GoldAndCurrencyPriceForDate);


        String[] args2={"get","from","NBP","AverageGoldPriceInPeriodOfTime","-start","2016-01-02","-end","2016-01-02"};
        nbpParser.parse(args2);

        strategy =nbpParser.getStrategy();

        assertTrue(strategy instanceof AverageGoldPriceInPeriodOfTime);


        String[] args3={"get","from","NBP","CurrencyWithTheBiggestAmplitude","-d","2016-01-02","-c","usd"};
        nbpParser.parse(args3);

        strategy =nbpParser.getStrategy();

        assertTrue(strategy instanceof CurrencyWithTheBiggestAmplitude);

        String[] args4={"get","from","NBP","CurrencyWithTheLowestValue","-d","2016-01-02"};
        nbpParser.parse(args4);

        strategy =nbpParser.getStrategy();

        assertTrue(strategy instanceof CurrencyWithTheLowestValue);

        String[] args5={"get","from","NBP","CurrencyExchangeRateSorted","-d","2016-01-02","-n","5"};
        nbpParser.parse(args5);

        strategy =nbpParser.getStrategy();

        assertTrue(strategy instanceof CurrencyExchangeRateSorted);

        String[] args6={"get","from","NBP","CurrencyTheHighestAndTheLowestRate","-c","pln"};
        nbpParser.parse(args6);

        strategy =nbpParser.getStrategy();

        assertTrue(strategy instanceof CurrencyTheHighestAndTheLowestRate);


        String[] args7={"get","from","NBP","CurrencyRateTable","-c","pln","-start","2016-01-02","-end","2016-01-02"};
        nbpParser.parse(args7);

        strategy =nbpParser.getStrategy();

        assertTrue(strategy instanceof CurrencyRateTable);

    }


}