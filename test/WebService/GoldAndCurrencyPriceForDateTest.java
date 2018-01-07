package WebService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoldAndCurrencyPriceForDateTest {

    @Test
    void makeQueryToServiceApi() {

        GoldAndCurrencyPriceForDate goldPrice=new GoldAndCurrencyPriceForDate();

        goldPrice.makeQueryToServiceApi();

        System.out.println(goldPrice.gold[0]);

    }
}