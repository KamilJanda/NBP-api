package WebService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyExchangeRateSortedTest {

    @Test
    void execute() {

        CurrencyExchangeRateSorted test=new CurrencyExchangeRateSorted("2017-05-08",1);

        assertEquals(test.execute(),"SDR (MFW) różnica pomiędzy ceną sprzedaży a ceną kupna: 0,11\n");


    }

}