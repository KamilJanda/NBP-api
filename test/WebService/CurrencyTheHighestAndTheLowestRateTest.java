package WebService;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyTheHighestAndTheLowestRateTest {

    @Test
    void getDifferenceDays() throws ParseException
    {
        CurrencyTheHighestAndTheLowestRate test=new CurrencyTheHighestAndTheLowestRate("usd");

        String start="2018-01-01";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date first=sdf.parse(start);
        Date second=new Date();


        long x=test.getDifferenceDays(first,second);

    }


    @Test
    void findMaxInPeriod(){
        CurrencyTheHighestAndTheLowestRate test=new CurrencyTheHighestAndTheLowestRate("usd");

        NBPCurrency testPeriod=test.createJsonNBPCurrency("http://api.nbp.pl/api/exchangerates/rates/a/usd/2016-01-13/2016-01-15/?format=json");

        PairOfCurrencyValueAndDate result=test.findMaxInPeriod(testPeriod);

        assertEquals(result.value,4.0411);
        assertEquals(result.date,"2016-01-15");
    }


    @Test
    void execute() {
        CurrencyTheHighestAndTheLowestRate test=new CurrencyTheHighestAndTheLowestRate("usd");


        System.out.println(test.execute());



    }

}