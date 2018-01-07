package WebService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;

public class GoldAndCurrencyPriceForDate implements Strategy {

    private NBPGold[] nbpGold;
    private NBPCurrency nbpCurrency;

    private String date;
    private String currency;

    GoldAndCurrencyPriceForDate(String date,String currency) {
        this.date=date;
        this.currency=currency.toLowerCase();
    }

    @Override
    public String makeQueryToServiceApi(String url) {
        StringBuilder response= new StringBuilder();

        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);
            in.close();
        }
        catch (IOException e)
        {
            e.getMessage();
            e.printStackTrace();
        }



        return response.toString();
    }

    @Override
    public void createJSON()
    {
        Pattern dataPattern=Pattern.compile("[1-2](\\d{3})-(\\d{2})-(\\d{2})");

        if(!dataPattern.matcher(date).matches())
            throw new IllegalArgumentException("Invalid data format, correct formta: yyyy-mm-dd");

        String goldInJSONFormat=makeQueryToServiceApi("http://api.nbp.pl/api/cenyzlota/"+date+"/?format=json");
        String currencyInJSONFormat=makeQueryToServiceApi("http://api.nbp.pl/api/exchangerates/rates/c/"+currency+"/"+date+"/?format=json");

        Gson gson= new GsonBuilder().create();
        this.nbpGold =gson.fromJson(goldInJSONFormat,NBPGold[].class);
        this.nbpCurrency=gson.fromJson(currencyInJSONFormat,NBPCurrency.class);
    }

    @Override
    public void execute() {
        
    }

    public NBPGold[] getNbpGold() {
        return nbpGold;
    }

    public NBPCurrency getNbpCurrency() {
        return nbpCurrency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
