package WebService;

import java.util.*;
import java.util.stream.Collectors;

public class CurrencyExchangeRateSorted extends NBPStrategy{

    private String date;
    private int numberOfRecordToPrint;

    private Map<String,Double> currencySellAndBuyDifference;

    public CurrencyExchangeRateSorted(String date, int numberOfRecordToPrint) {
        this.date = date;
        this.numberOfRecordToPrint = numberOfRecordToPrint;
        urlCurrencyQueryArray="http://api.nbp.pl/api/exchangerates/tables/c/"+date+"/?format=json";
        this.currencySellAndBuyDifference=new TreeMap<>();
    }

    private void generateCurrencySellAndBuyDifference()
    {
        Double difference=0.0;
        for (Rates rates:nbpCurrencyArray[0].getRates()
             ) {
            difference=rates.getAsk()-rates.getBid();
            currencySellAndBuyDifference.put(rates.getCurrency(),difference);
        }
    }


    @Override
    public String execute() {
        createJsonObject();
        generateCurrencySellAndBuyDifference();

        Map<String,Double> sortedNewMap = currencySellAndBuyDifference.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        StringBuilder result=new StringBuilder();

        int i=0;
        for (Map.Entry<String ,Double> record: currencySellAndBuyDifference.entrySet()
             ) {
                if(i>=this.numberOfRecordToPrint) break;
            result.append(record.getKey());
            result.append(" różnica pomiędzy ceną sprzedaży a ceną kupna: ");
            result.append(String.format("%.2f",record.getValue()));
            result.append("\n");
            i++;
        }


        //sortedNewMap.forEach((key,val)->{result.append(key);result.append(" różnica pomiędzy ceną sprzedaży a ceną kupna: ");result.append(val);result.append("\numberOfRecordToPrint");});


        return result.toString();
    }
}
