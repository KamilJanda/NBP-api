package WebService;

//import sun.util.resources.Bundles;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public abstract class NBPStrategy implements IStrategy{

    protected NBPGold[] nbpGold;
    protected NBPCurrency nbpCurrency;
    protected NBPCurrency[] nbpCurrencyArray;

    //protected String date;
    //protected String currency;

    protected String urlGoldQuery;
    protected String urlCurrencyQuery;
    protected String urlCurrencyQueryArray;


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
        catch (FileNotFoundException e)
        {
            e.getMessage();
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
        if(urlCurrencyQuery!=null)
            nbpCurrency=createJsonNBPCurrency(urlCurrencyQuery);
        if(urlGoldQuery!=null)createJsonNBPGold();
        if(urlCurrencyQueryArray!=null)
            nbpCurrencyArray=createJsonNBPCurrencyAsArray(urlCurrencyQueryArray);

    }

    protected NBPCurrency createJsonNBPCurrency(String urlCurrencyQuery)
    {
        String currencyInJSONFormat=makeQueryToServiceApi(urlCurrencyQuery);

        Gson gson= new GsonBuilder().create();
        return gson.fromJson(currencyInJSONFormat,NBPCurrency.class);
    }

    protected NBPCurrency[] createJsonNBPCurrencyAsArray(String urlCurrencyQueryArray)
    {
        String currencyInJSONFormat=makeQueryToServiceApi(urlCurrencyQueryArray);

        Gson gson= new GsonBuilder().create();
        return gson.fromJson(currencyInJSONFormat,NBPCurrency[].class);
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

    public String getUrlGoldQuery() {
        return urlGoldQuery;
    }

    public String getUrlCurrencyQuery() {
        return urlCurrencyQuery;
    }
}
