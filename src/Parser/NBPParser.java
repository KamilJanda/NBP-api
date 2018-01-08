package Parser;

import WebService.AverageGoldPriceInPeriodOfTime;
import WebService.GoldAndCurrencyPriceForDate;
import WebService.IStrategy;

public class NBPParser extends Parser {



    @Override
    public void parse(String[] args) {
        int argsLength=args.length;

        if (argsLength<4) throw new IllegalArgumentException("Invalid number of arguments after NBP");

        args[3]=args[3].toLowerCase();

        switch (args[3])
        {
            case "averagegoldpriceinperiodoftime":
                this.strategy = this.AverageGoldPriceInPeriodOfTimeCreator(args);
                break;
            case "goldandcurrencypricefordate":
                this.strategy = this.GoldAndCurrencyPriceForDateCreator(args);
                break;
            default:
                throw new IllegalArgumentException("Unknown strategy");
        }
    }

    private AverageGoldPriceInPeriodOfTime AverageGoldPriceInPeriodOfTimeCreator(String[] args)
    {
        int argsLength=args.length;
        int correctLengthOfArgs=8;
        int indexAfterFunctionName=4;
        String startDate=null, endDate=null;

        if (argsLength!=correctLengthOfArgs) throw new  IllegalArgumentException("Invalid number of arguments after AverageGoldPriceInPeriodOfTime");

        for(int i=indexAfterFunctionName;i<argsLength;i++)
        {
            if(args[i].equals("-start"))
            {
                i++;
                startDate=args[i];
            }
            else if (args[i].equals("-end"))
            {
                i++;
                endDate=args[i];
            }
        }

        if (startDate==null||endDate==null) throw new IllegalArgumentException("Incorrect syntax specify date \"-d yyyy-mm-dd\" or specify currency \"-c PLN\"");

        return new AverageGoldPriceInPeriodOfTime(startDate,endDate);
    }

    private GoldAndCurrencyPriceForDate GoldAndCurrencyPriceForDateCreator(String[] args)
    {
        int argsLength=args.length;
        int correctLengthOfArgs=8;
        int indexAfterFunctionName=4;
        String date=null, currency=null;

        if (argsLength!=correctLengthOfArgs) throw new  IllegalArgumentException("Invalid number of arguments after AverageGoldPriceInPeriodOfTime");

        for(int i=indexAfterFunctionName;i<argsLength;i++)
        {
            if(args[i].equals("-d"))
            {
                i++;
                date=args[i];
            }
            else if (args[i].equals("-c"))
            {
                i++;
                currency=args[i];
            }
        }

        if (date==null||currency==null) throw new IllegalArgumentException("Incorrect syntax specify date \"-d yyyy-mm-dd\" or specify currency \"-c PLN\"");

        return new GoldAndCurrencyPriceForDate(date,currency);
    }


}
