package WebService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Strategy use to find currency with the biggest amplitude form chosen day to today
 */
public class CurrencyWithTheBiggestAmplitude extends NBPStrategy {

    private String startDate;
    private String currency;

    private SimpleDateFormat sdf;

    private PairOfCurrencyValueAndDate min;
    private PairOfCurrencyValueAndDate max;

    public CurrencyWithTheBiggestAmplitude(String startDate) {
        this.currency=currency;
        this.startDate=startDate;
        min=new PairOfCurrencyValueAndDate("",Double.MAX_VALUE);
        max=new PairOfCurrencyValueAndDate("",-1);
        sdf = new SimpleDateFormat("yyyy-MM-dd");

        //urlCurrencyQuery="http://api.nbp.pl/api/exchangerates/tables/a/{startDate}/today/?format=json";

    }


    private double findCurrencyWithTheBiggestAmplitude(String currency)
    {
        CurrencyTheHighestAndTheLowestRate currencyMaxAndMin=new CurrencyTheHighestAndTheLowestRate(currency);
        currencyMaxAndMin.setDateOfFirstRecordInNBP(startDate);
        try {
            currencyMaxAndMin.findCurrencyTheHighestAndTheLowestRate();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        double max=currencyMaxAndMin.getMax().value;
        double min=currencyMaxAndMin.getMin().value;

        return max-min;
    }

    private String findKeyWithMaxVal(Map<String,Double> map)
    {
        Double max=0.0;
        String keyMax=null;

        for (Map.Entry<String,Double> record:map.entrySet()
             ) {
            if(record.getValue()>max) {
                max=record.getValue();
                keyMax = record.getKey();
            }
        }

        return keyMax;
    }

    private String findCurrencyWithTheBiggestAmplitude()
    {
        String result=null;
        String url="http://api.nbp.pl/api/exchangerates/tables/a/?format=json";
        
        nbpCurrencyArray = createJsonNBPCurrencyAsArray(url);
        
        Map<String,Double> currencyAndAmplitude=new HashMap<>();

        double diff=0;
        for (Rates rates:nbpCurrencyArray[0].getRates()
             ) {
            diff= findCurrencyWithTheBiggestAmplitude(rates.getCode());
            currencyAndAmplitude.put(rates.getCurrency(),diff);
        }


        String keyMax=findKeyWithMaxVal(currencyAndAmplitude);
        Double valMax=currencyAndAmplitude.get(keyMax);



        return "Waluta z najwieksza ampitutda to: "+keyMax+" amplituda: "+valMax;
    }

    @Override
    public String execute() {
        String result=null;

        try {
            //result=findCurrencyWithTheBiggestAmplitude();
            result= findCurrencyWithTheBiggestAmplitude();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }
}
