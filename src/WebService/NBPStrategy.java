package WebService;

//import sun.util.resources.Bundles;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public abstract class NBPStrategy implements IStrategy{

    protected NBPGold[] nbpGold;
    protected NBPCurrency nbpCurrency;

    protected String date;
    protected String currency;

    protected String urlGoldQuery;
    protected String urlCurrencyQuery;


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
    public void createJsonObject()
    {
        /*
        Pattern dataPattern=Pattern.compile("[1-2](\\d{3})-(\\d{2})-(\\d{2})");

        if(!dataPattern.matcher(date).matches())
            throw new IllegalArgumentException("Invalid data format, correct formta: yyyy-mm-dd");
        */
        if(urlCurrencyQuery!=null)createJsonNBPCurrency();
        if(urlGoldQuery!=null)createJsonNBPGold();

    }

    private void createJsonNBPCurrency()
    {
        String currencyInJSONFormat=makeQueryToServiceApi(urlCurrencyQuery);

        Gson gson= new GsonBuilder().create();
        this.nbpCurrency=gson.fromJson(currencyInJSONFormat,NBPCurrency.class);
    }

    private void createJsonNBPGold()
    {
        String goldInJSONFormat=makeQueryToServiceApi(urlGoldQuery);


        Gson gson= new GsonBuilder().create();
        this.nbpGold =gson.fromJson(goldInJSONFormat,NBPGold[].class);
    }


    @Override
    public abstract String execute();


    public NBPGold[] getNbpGold() {
        return nbpGold;
    }

    public NBPCurrency getNbpCurrency() {
        return nbpCurrency;
    }

    public String getDate() {
        return date;
    }

    public String getCurrency() {
        return currency;
    }

    public String getUrlGoldQuery() {
        return urlGoldQuery;
    }

    public String getUrlCurrencyQuery() {
        return urlCurrencyQuery;
    }
}
