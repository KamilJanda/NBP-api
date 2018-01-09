package WebService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Strategy use to find currency with the biggest amplitude form chosen day to today
 */
public class CurrencyWithTheBiggestAmplitude extends NBPStrategy {

    private String startDate;
    private String currency;

    private SimpleDateFormat sdf;

    private PairOfCurrencyValueAndDate min;
    private PairOfCurrencyValueAndDate max;

    public CurrencyWithTheBiggestAmplitude(String startDate,String currency) {
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


    private void findCurrencyWithTheBiggestAmplitude() throws ParseException, CloneNotSupportedException
    {

        Date today=new Date();

        int maxDayForSingleQuery=93;
        Date startDate=sdf.parse(this.startDate);
        Date endDate= DateUtility.addDays(startDate,maxDayForSingleQuery);

        String start;
        String end;
        String url;

        double localMax=0.0;
        double localMin=Double.MAX_VALUE;

        Map<String,Double> maxForCurrency=new HashMap<>();
        Map<String,Double> minForCurrency=new HashMap<>();


        while (endDate.before(today))
        {
            start=sdf.format(startDate);
            end=sdf.format(endDate);

            url="http://api.nbp.pl/api/exchangerates/tables/a/"+startDate+"/"+endDate+"/?format=json";

            nbpCurrencyArray=createJsonNBPCurrencyAsArray(url);

            localMax=findMaxInPeriod(nbpCurrency);
            localMin=findMinInPeriod(nbpCurrency);


/*
            if(max.compareTo(localMax)==-1)
                max=(PairOfCurrencyValueAndDate)localMax.clone();

            if (min.compareTo(localMin)==1)
                min=(PairOfCurrencyValueAndDate) localMin.clone();
*/
            startDate=endDate;
            endDate= DateUtility.addDays(startDate,maxDayForSingleQuery);
        }

        endDate=today;

        start=sdf.format(startDate);
        end=sdf.format(endDate);

        url="http://api.nbp.pl/api/exchangerates/rates/a/usd/"+start+"/"+end+"/?format=json";

        nbpCurrency=createJsonNBPCurrency(url);

        localMax=findMaxInPeriod(nbpCurrency);
        localMin=findMinInPeriod(nbpCurrency);
/*
        if(max.compareTo(localMax)==-1)
            max=(PairOfCurrencyValueAndDate)localMax.clone();

        if (min.compareTo(localMin)==1)
            min=(PairOfCurrencyValueAndDate) localMin.clone();
*/

    }

    @Override
    public String execute() {
        return null;
    }
}
