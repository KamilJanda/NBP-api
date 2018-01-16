package WebService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PairOfCurrencyValueAndDateTest {
    @Test
    void compareTo() {

        PairOfCurrencyValueAndDate smaller=new PairOfCurrencyValueAndDate();
        PairOfCurrencyValueAndDate bigger=new PairOfCurrencyValueAndDate();
        PairOfCurrencyValueAndDate equal=new PairOfCurrencyValueAndDate();

        smaller.value=1;
        bigger.value=1.5;
        equal.value=1;

        assertEquals(smaller.compareTo(bigger),-1);
        assertEquals(smaller.compareTo(equal),0);
        assertEquals(bigger.compareTo(smaller),1);




    }

    @Test
    void cloneTest()
    {
        PairOfCurrencyValueAndDate smaller=new PairOfCurrencyValueAndDate();
        smaller.value=1;
        smaller.date="1901-01-01";



        try {
            PairOfCurrencyValueAndDate test=(PairOfCurrencyValueAndDate) smaller.clone();
            assertFalse(test==smaller);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


    }



}