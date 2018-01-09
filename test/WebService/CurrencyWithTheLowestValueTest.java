package WebService;

import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

class CurrencyWithTheLowestValueTest {
    @Test
    void execute() {

        CurrencyWithTheLowestValue test=new CurrencyWithTheLowestValue("2017-12-11");

        assertEquals(test.execute(),"Waluta z najnizszym kursem kupna w podanym dniu to: forint (Węgry)");



    }

}