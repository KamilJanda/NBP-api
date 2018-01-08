package WebService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;

public class GoldAndCurrencyPriceForDate extends NBPStrategy {

    public GoldAndCurrencyPriceForDate(String date,String currency) {
        this.date=date;
        this.currency=currency.toLowerCase();
        this.urlGoldQuery="http://api.nbp.pl/api/cenyzlota/"+date+"/?format=json";
        this.urlCurrencyQuery="http://api.nbp.pl/api/exchangerates/rates/a/"+currency+"/"+date+"/?format=json";
    }

    @Override
    public String execute() {
        this.createJsonObject();

        StringBuilder result=new StringBuilder("");

        result.append("Kurs średni waluty ");
        result.append(currency);
        result.append(" w dniu ");
        result.append(date);
        result.append(" to: ");
        result.append(nbpCurrency.getRates()[0].getMid());

        return result.toString();
    }

}
