package WebService;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyRateTableTest {

    @Test
    void sortByWeekDay()
    {
        CurrencyRateTable test=new CurrencyRateTable("2018-01-01","2018-01-09","usd");
        List<Double>[] result=null;

        try {
            result=test.sortByWeekDay();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        Double firstMonday=3.4546;
        Double secondMonday=3.4992;
        assertEquals(result[1].get(0),firstMonday);
        assertEquals(result[1].get(1),secondMonday);


    }

    @Test
    void execute() {
        CurrencyRateTable test=new CurrencyRateTable("2018-01-01","2018-01-09","usd");

        //System.out.println(test.execute());

    }

}