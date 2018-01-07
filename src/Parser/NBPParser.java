package Parser;

import WebService.AverageGoldPriceInPeriodOfTime;
import WebService.GoldAndCurrencyPriceForDate;
import WebService.Strategy;

public class NBPParser extends Parser {

    private Strategy operation;

    @Override
    public void parse(String[] args) {
        int argsLength=args.length;

        if (argsLength<4) throw new IllegalArgumentException("Invalid number of arguments after NBP");

        args[3]=args[3].toLowerCase();

        switch (args[3])
        {
            case "averagegoldpriceinperiodoftime":
                this.operation = this.AverageGoldPriceInPeriodOfTimeCreator(args);
                break;
            case "goldandcurrencypricefordate":
                this.operation= this.GoldAndCurrencyPriceForDateCreator(args);
                break;
            default:
                throw new IllegalArgumentException("Unknown operation");
        }
    }

    private AverageGoldPriceInPeriodOfTime AverageGoldPriceInPeriodOfTimeCreator(String[] args)
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

        if (date==null||currency==null) throw new IllegalArgumentException("Incorrect syntax specify date \"-d 1901-01-01\" or specify currency \"-c PLN\"");

        return new AverageGoldPriceInPeriodOfTime(date,currency);
    }

    private GoldAndCurrencyPriceForDate GoldAndCurrencyPriceForDateCreator(String[] args)
    {


        //return new GoldAndCurrencyPriceForDate();
        return null;
    }


    public Strategy getOperation() {
        return operation;
    }
}
