package WebService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyWithTheBiggestAmplitudeTest {
    @Test
    void findMaxInPeriod() {
    }

    @Test
    void findMinInPeriod() {
    }


    @Test
    void execute() {

        CurrencyWithTheBiggestAmplitude test=new CurrencyWithTheBiggestAmplitude("2016-01-01");

        String result = test.execute();
        String expected = "Waluta z najwieksza ampitutda to: funt szterling amplituda: 1.3106";

        assertEquals(result,expected);


    }

}