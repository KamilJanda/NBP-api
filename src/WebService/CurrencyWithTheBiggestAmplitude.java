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

    double findMaxInPeriod(NBPCurrency nbpCurrency)
    {
        double max=0;


        for (Rates record:nbpCurrency.getRates()
                ) {
            if(record.getMid()>max)
            {
                max=record.getMid();

            }
        }

        return max;
    }

    double findMinInPeriod(NBPCurrency nbpCurrency)
    {
        double min=Double.MAX_VALUE;


        for (Rates record:nbpCurrency.getRates()
                ) {
            if(record.getMid()<min)
            {
                min=record.getMid();

            }
        }

        return min;
    }


    private String findCurrencyWithTheBiggestAmplitude() throws ParseException, CloneNotSupportedException
    {

        String result=null;
        Date today=new Date();

        int maxDayForSingleQuery=93;
        Date startDate=sdf.parse(this.startDate);
        Date endDate= DateUtility.addDays(startDate,maxDayForSingleQuery);

        String start=null;
        String end=null;
        String url=null;

        double localMax=0.0;
        double localMin=Double.MAX_VALUE;

        Map<String,Double> maxForCurrency=new HashMap<>();
        Map<String,Double> minForCurrency=new HashMap<>();

        Map<String,Double> diff=new HashMap<>();


        while (endDate.before(today))
        {
            start=sdf.format(startDate);
            end=sdf.format(endDate);

            url="http://api.nbp.pl/api/exchangerates/tables/a/"+startDate+"/"+endDate+"/?format=json";

            nbpCurrencyArray=createJsonNBPCurrencyAsArray(url);

            for (NBPCurrency record:nbpCurrencyArray
                 ) {

                for(Rates rates:record.getRates())
                {
                    String currency=rates.getCurrency();
                    if(maxForCurrency.containsKey(currency))
                    {
                        if(rates.getMid()>maxForCurrency.get(currency))
                        {
                            maxForCurrency.replace(currency,rates.getMid());
                        }
                    }
                    else
                    {
                        maxForCurrency.put(currency,rates.getMid());
                    }


                    if(minForCurrency.containsKey(currency))
                    {
                        if(rates.getMid()<minForCurrency.get(currency))
                        {
                            minForCurrency.replace(currency,rates.getMid());
                        }
                    }
                    else
                    {
                        minForCurrency.put(currency,rates.getMid());
                    }


                }

            }

            startDate=endDate;
            endDate= DateUtility.addDays(startDate,maxDayForSingleQuery);
        }




        endDate=today;

        url="http://api.nbp.pl/api/exchangerates/tables/a/"+startDate+"/"+endDate+"/?format=json";

        nbpCurrencyArray=createJsonNBPCurrencyAsArray(url);

        for (NBPCurrency record:nbpCurrencyArray
                ) {

            for(Rates rates:record.getRates())
            {
                String currency=rates.getCurrency();
                if(maxForCurrency.containsKey(currency))
                {
                    if(rates.getMid()>maxForCurrency.get(currency))
                    {
                        maxForCurrency.replace(currency,rates.getMid());
                    }
                }
                else
                {
                    maxForCurrency.put(currency,rates.getMid());
                }


                if(minForCurrency.containsKey(currency))
                {
                    if(rates.getMid()<minForCurrency.get(currency))
                    {
                        minForCurrency.replace(currency,rates.getMid());
                    }
                }
                else
                {
                    minForCurrency.put(currency,rates.getMid());
                }


            }

        }


        for (Map.Entry<String, Double> entry : maxForCurrency.entrySet()) {
            String key = entry.getKey();
            Double max = entry.getValue();
            Double min = minForCurrency.get(key);
            diff.put(key,max-min);
        }

        Map<String,Double> sortedNewMap = diff.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));


        Map.Entry<String,Double> entry = sortedNewMap.entrySet().iterator().next();
        String key = entry.getKey();
        Double value = entry.getValue();

        return key+" różnica: "+value;
    }

    @Override
    public String execute() {
        String result=null;

        try {
            result=findCurrencyWithTheBiggestAmplitude();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }
}
