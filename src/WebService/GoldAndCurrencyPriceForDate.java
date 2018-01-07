package WebService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

public class GoldAndCurrencyPriceForDate implements Strategy {

    public NBPGold[] gold;
    private NBPCurrency currency;


    @Override
    public void makeQueryToServiceApi() {

        try {
            URL oracle = new URL("http://api.nbp.pl/api/cenyzlota/?format=json");
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            Gson gson= new GsonBuilder().create();
            this.gold=gson.fromJson(in,NBPGold[].class);

            in.close();


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }





    }





    public ParsedObject getCurrency() {
        return currency;
    }
}
