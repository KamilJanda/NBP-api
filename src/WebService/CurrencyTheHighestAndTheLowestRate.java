package WebService;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CurrencyTheHighestAndTheLowestRate extends NBPStrategy {

    private String currency;
    // Calendar cal;
    private SimpleDateFormat sdf;
    private String dateOfFirstRecordInNBP;

    private PairOfCurrencyValueAndDate min;
    private PairOfCurrencyValueAndDate max;


    public CurrencyTheHighestAndTheLowestRate(String currency) {
        this.currency = currency;
        //cal = Calendar.getInstance();
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        dateOfFirstRecordInNBP="2002-01-02";
        min=new PairOfCurrencyValueAndDate("",Double.MAX_VALUE);
        max=new PairOfCurrencyValueAndDate("",0);

    }

    long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    PairOfCurrencyValueAndDate findMaxInPeriod(NBPCurrency nbpCurrency)
    {
        PairOfCurrencyValueAndDate max=new PairOfCurrencyValueAndDate();

        max.date="";
        max.value=0;

        for (Rates record:nbpCurrency.getRates()
             ) {
            if(record.getMid()>max.value)
            {
                max.value=record.getMid();
                max.date=record.getEffectiveDate();
            }
        }

        return max;
    }

    PairOfCurrencyValueAndDate findMinInPeriod(NBPCurrency nbpCurrency)
    {
        PairOfCurrencyValueAndDate min=new PairOfCurrencyValueAndDate();

        min.date="";
        min.value=Double.MAX_VALUE;

        for (Rates record:nbpCurrency.getRates()
                ) {
            if(record.getMid()<min.value)
            {
                min.value=record.getMid();
                min.date=record.getEffectiveDate();
            }
        }

        return min;
    }


    void findCurrencyTheHighestAndTheLowestRate() throws ParseException, CloneNotSupportedException
    {

        Date today=new Date();

        int maxDayForSingleQuery=93;
        Date startDate=sdf.parse(dateOfFirstRecordInNBP);
        Date endDate= DateUtility.addDays(startDate,maxDayForSingleQuery);

        String start=null;
        String end=null;
        String url=null;

        PairOfCurrencyValueAndDate localMax=null;
        PairOfCurrencyValueAndDate localMin=null;

        while (endDate.before(today))
        {
            start=sdf.format(startDate);
            end=sdf.format(endDate);

            url="http://api.nbp.pl/api/exchangerates/rates/a/"+currency+"/"+start+"/"+end+"/?format=json";

            nbpCurrency = createJsonNBPCurrency(url);

            if(nbpCurrency!=null) {
                localMax = findMaxInPeriod(nbpCurrency);
                localMin = findMinInPeriod(nbpCurrency);

                if (max.compareTo(localMax) == -1)
                    max = (PairOfCurrencyValueAndDate) localMax.clone();

                if (min.compareTo(localMin) == 1)
                    min = (PairOfCurrencyValueAndDate) localMin.clone();
            }
            startDate=endDate;
            endDate= DateUtility.addDays(startDate,maxDayForSingleQuery);
        }

        endDate=today;

        start=sdf.format(startDate);
        end=sdf.format(endDate);

        url="http://api.nbp.pl/api/exchangerates/rates/a/"+currency+"/"+start+"/"+end+"/?format=json";

        nbpCurrency=createJsonNBPCurrency(url);
        if(nbpCurrency!=null) {
            localMin = findMinInPeriod(nbpCurrency);
            localMax = findMaxInPeriod(nbpCurrency);

            if (max.compareTo(localMax) == -1)
                max = (PairOfCurrencyValueAndDate) localMax.clone();

            if (min.compareTo(localMin) == 1)
                min = (PairOfCurrencyValueAndDate) localMin.clone();
        }

    }

    @Override
    public String execute() {

        try {
            findCurrencyTheHighestAndTheLowestRate();
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        catch (ParseException e)
        {
            e.getMessage();
            e.printStackTrace();
        }


        return "Maximum : "+max.value+" w dniu "+max.date+"\nMin: "+min.value+" w dniu: "+min.date;
    }

    public String getDateOfFirstRecordInNBP() {
        return dateOfFirstRecordInNBP;
    }

    public void setDateOfFirstRecordInNBP(String dateOfFirstRecordInNBP) {
        this.dateOfFirstRecordInNBP = dateOfFirstRecordInNBP;
    }

    public PairOfCurrencyValueAndDate getMin() {
        return min;
    }

    public PairOfCurrencyValueAndDate getMax() {
        return max;
    }
}
