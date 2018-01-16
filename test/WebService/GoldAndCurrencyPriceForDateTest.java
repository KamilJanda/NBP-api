package WebService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoldAndCurrencyPriceForDateTest {

    @Test
    void makeQueryToServiceApi() {

        GoldAndCurrencyPriceForDate goldAndCurrencyPriceForDate=new GoldAndCurrencyPriceForDate("2013-01-02","usd");

        String goldResult=goldAndCurrencyPriceForDate.makeQueryToServiceApi("http://api.nbp.pl/api/cenyzlota/2013-01-02/?format=json");
        String goldExpected="[{\"data\":\"2013-01-02\",\"cena\":165.83}]";
        
        String currencyResult=goldAndCurrencyPriceForDate.makeQueryToServiceApi("http://api.nbp.pl/api/exchangerates/rates/c/usd/2013-01-02/?format=json");
        String currencyExpected="{\"table\":\"C\",\"currency\":\"dolar amerykański\",\"code\":\"USD\",\"rates\":[{\"no\":\"001/C/NBP/2013\",\"effectiveDate\":\"2013-01-02\",\"bid\":3.0686,\"ask\":3.1306}]}";
        
        
        assertEquals(goldResult,goldExpected);
        assertEquals(currencyResult,currencyExpected);



        
    }

    @Test
    void createJSON() {

        NBPStrategy goldAndCurrencyPriceForDate=new GoldAndCurrencyPriceForDate("2013-01-02","usd");

        goldAndCurrencyPriceForDate.createJsonObject();
        
        Double priceInTestedDay=165.83;
        String testedDate="2013-01-02";

        assertEquals(goldAndCurrencyPriceForDate.getNbpGold()[0].getCena(),priceInTestedDay);
        assertEquals(goldAndCurrencyPriceForDate.getNbpGold()[0].getData(),testedDate);




    }

    @Test
    void execute()
    {
        GoldAndCurrencyPriceForDate test=new GoldAndCurrencyPriceForDate("2016-02-04","gbp");

        String result = test.execute();
        String expected = "Kurs średni waluty gbp to: 5.7822 ,kurs zlota to: 146.78 w dniu 2016-02-04";

        assertEquals(result,expected);

    }


}