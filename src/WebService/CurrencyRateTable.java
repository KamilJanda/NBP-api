package WebService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CurrencyRateTable extends NBPStrategy {

    private String startDate;
    private String endDate;
    private String currency;

    private SimpleDateFormat myFormat;

    public CurrencyRateTable(String startDate, String endDate,String currency) {
        this.myFormat=new SimpleDateFormat("yyyy-MM-dd");
        this.startDate = startDate;
        this.endDate = endDate;
        this.currency=currency;
        urlCurrencyQuery="http://api.nbp.pl/api/exchangerates/rates/a/"+currency+"/"+startDate+"/"+endDate+"/?format=json";
    }

    List<Double>[] sortByWeekDay() throws ParseException
    {
        createJsonObject();

        List<Double>[] result=new List[5];
        Calendar calendar=Calendar.getInstance();
        Date date=null;
        int dayOfWeek=0;

        for (int i=0;i<5;i++)
            result[i]=new ArrayList<>();

        for (Rates rates: nbpCurrency.getRates()
             ) {
            date=myFormat.parse(rates.getEffectiveDate());
            calendar.setTime(date);
            dayOfWeek=calendar.get(Calendar.DAY_OF_WEEK);

            switch (dayOfWeek)
            {
                case 2: result[0].add(rates.getMid());
                    break;
                case 3: result[1].add(rates.getMid());
                    break;
                case 4: result[2].add(rates.getMid());
                    break;
                case 5: result[3].add(rates.getMid());
                    break;
                case 6: result[4].add(rates.getMid());
                    break;
                default:
                    break;

            }

        }

        return result;
    }


    private String createAscii(int numbers, String ascii)
    {
        String result="";

        for(int i=0;i<numbers;i++)
            result+=ascii;

        return result;
    }


    @Override
    public String execute() {

        List<Double>[] sortedByWeekDay=null;
        StringBuilder result=new StringBuilder();
        String[] weekDayNames={"Mon","Tue","Wed","Thu","Fir"};

        try {
            sortedByWeekDay=sortByWeekDay();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        for (int weekDay=0;weekDay<sortedByWeekDay.length;weekDay++) {

            for (int j=0;j<sortedByWeekDay[weekDay].size();j++)
            {
                result.append(weekDayNames[weekDay]);
                result.append(j+1);

                Double val=sortedByWeekDay[weekDay].get(j)*100;

                int scale=val.intValue();

                String ascii=createAscii(scale-300,"*");

                result.append(": ");
                result.append(ascii);
                result.append(" ");
                result.append(sortedByWeekDay[weekDay].get(j));
                result.append("\n");
            }


        }

        return result.toString();
    }
}
