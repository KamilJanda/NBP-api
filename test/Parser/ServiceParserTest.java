package Parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceParserTest {
    @Test
    void parse() {

        String[] args=new String[3];

        args[0]="get";
        args[1]="from";
        args[2]="NBP";

        ServiceParser serviceParser=new ServiceParser();
        try {
            serviceParser.parse(args);
        }
        catch (IllegalArgumentException e)
        {
            assertTrue(e.getMessage().equals("Invalid number of arguments after NBP"));
        }

        assertTrue(serviceParser.getServiceParser() instanceof NBPParser);






    }

}