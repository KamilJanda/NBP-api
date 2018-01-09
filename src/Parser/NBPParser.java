package Parser;

import WebService.*;

public class NBPParser extends Parser {



    @Override
    public void parse(String[] args) {
        int argsLength=args.length;

        if (argsLength<4) throw new IllegalArgumentException("Invalid number of arguments after NBP");

        args[3]=args[3].toLowerCase();

        switch (args[3])
        {
            case "averagegoldpriceinperiodoftime":
                this.strategy = this.creatorAverageGoldPriceInPeriodOfTime(args);
                break;
            case "goldandcurrencypricefordate":
                this.strategy = this.creatorGoldAndCurrencyPriceForDate(args);
                break;
            case "currencywiththebiggestamplitude":
                this.strategy = this.creatorCurrencyWithTheBiggestAmplitude(args);
                break;
            case "currencywiththelowestvalue":
                strategy=creatorCurrencyWithTheLowestValue(args);
                break;
            case "currencyexchangeratesorted":
                strategy=creatorCurrencyExchangeRateSorted(args);
                break;
            case "currencythehighestandthelowestrate":
                strategy=createCurrencyTheHighestAndTheLowestRate(args);
                break;
            case "currencyratetable":
                strategy=createCurrencyRateTable(args);
                break;
            default:
                throw new IllegalArgumentException("Unknown strategy");
        }
    }

    private AverageGoldPriceInPeriodOfTime creatorAverageGoldPriceInPeriodOfTime(String[] args)
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

    private GoldAndCurrencyPriceForDate creatorGoldAndCurrencyPriceForDate(String[] args)
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

    private CurrencyWithTheBiggestAmplitude creatorCurrencyWithTheBiggestAmplitude(String[] args)
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

        return new CurrencyWithTheBiggestAmplitude(date,currency);
    }

    private CurrencyWithTheLowestValue creatorCurrencyWithTheLowestValue(String[] args)
    {
        int argsLength=args.length;
        int correctLengthOfArgs=6;
        int indexAfterFunctionName=4;
        String date=null;

        if (argsLength!=correctLengthOfArgs) throw new  IllegalArgumentException("Invalid number of arguments after AverageGoldPriceInPeriodOfTime");

        for(int i=indexAfterFunctionName;i<argsLength;i++)
        {
            if(args[i].equals("-d"))
            {
                i++;
                date=args[i];
            }
        }

        if (date==null) throw new IllegalArgumentException("Incorrect syntax specify date \"-d yyyy-mm-dd\" or specify currency \"-c PLN\"");

        return new CurrencyWithTheLowestValue(date);
    }

    private CurrencyExchangeRateSorted creatorCurrencyExchangeRateSorted(String[] args)
    {
        int argsLength=args.length;
        int correctLengthOfArgs=8;
        int indexAfterFunctionName=4;
        String date=null;
        int n=0;

        if (argsLength!=correctLengthOfArgs) throw new  IllegalArgumentException("Invalid number of arguments after AverageGoldPriceInPeriodOfTime");

        for(int i=indexAfterFunctionName;i<argsLength;i++)
        {
            if(args[i].equals("-d"))
            {
                i++;
                date=args[i];
            }
            else if(args[i].equals("-n"))
            {
                i++;
                n=Integer.parseInt(args[i]);
            }
        }

        if (date==null) throw new IllegalArgumentException("Incorrect syntax specify date \"-d yyyy-mm-dd\" or specify currency \"-c PLN\"");

        return new CurrencyExchangeRateSorted(date,n);
    }

    private CurrencyTheHighestAndTheLowestRate createCurrencyTheHighestAndTheLowestRate(String[] args)
    {
        int argsLength=args.length;
        int correctLengthOfArgs=6;
        int indexAfterFunctionName=4;
        String currency=null;

        if (argsLength!=correctLengthOfArgs) throw new  IllegalArgumentException("Invalid number of arguments after AverageGoldPriceInPeriodOfTime");

        for(int i=indexAfterFunctionName;i<argsLength;i++)
        {
            if (args[i].equals("-c"))
            {
                i++;
                currency=args[i];
            }
        }

        if (currency==null) throw new IllegalArgumentException("Incorrect syntax specify date \"-d yyyy-mm-dd\" or specify currency \"-c PLN\"");

        return new CurrencyTheHighestAndTheLowestRate(currency);
    }

    private CurrencyRateTable createCurrencyRateTable(String[] args)
    {
        int argsLength=args.length;
        int correctLengthOfArgs=10;
        int indexAfterFunctionName=4;
        String startDate=null, endDate=null,currency=null;

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
            else if (args[i].equals("-c"))
            {
                i++;
                currency=args[i];
            }

        }

        if (startDate==null||endDate==null) throw new IllegalArgumentException("Incorrect syntax specify date \"-d yyyy-mm-dd\" or specify currency \"-c PLN\"");

        return new CurrencyRateTable(startDate,endDate,currency);
    }

}
