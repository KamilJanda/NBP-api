package WebService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AverageGoldPriceInPeriodOfTimeTest {

    @Test
    void countAvgPrice()
    {

    }

    @Test
    void execute() {

        WebService testWebService=new NBPWebService(new AverageGoldPriceInPeriodOfTime("2016-05-15","2016-05-17"));

        assertEquals(testWebService.print(),"Srednia cena w podanym okresie to: 158,75");


    }

}