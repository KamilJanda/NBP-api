package Parser;

import WebService.AverageGoldPriceInPeriodOfTime;
import WebService.Strategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NBPParserTest {


    @Test
    void parse()
    {
        NBPParser nbpParser=new NBPParser();
        String[] args={"get","from","NBP","AverageGoldPriceInPeriodOfTime","-c","PLN","-d","2016-01-01"};


        nbpParser.parse(args);




        Strategy strategy=nbpParser.getOperation();

        assertTrue(strategy instanceof AverageGoldPriceInPeriodOfTime);


    }


}