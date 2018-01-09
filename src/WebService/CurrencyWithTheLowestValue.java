package WebService;

public class CurrencyWithTheLowestValue extends NBPStrategy {

    private String date;

    public CurrencyWithTheLowestValue(String date) {
        this.date = date;
        urlCurrencyQueryArray="http://api.nbp.pl/api/exchangerates/tables/c/"+date+"/?format=json";
    }

    private String findCurrencyWithTheLowestValue()
    {
        double min=Double.MAX_VALUE;
        String currency=null;
        for (Rates rates:nbpCurrencyArray[0].getRates()
             ) {
            if(rates.getBid()<min)
            {
                min=rates.getBid();
                currency=rates.getCurrency();
            }
        }

        return currency;
    }

    @Override
    public String execute() {
        createJsonObject();

        String currency=findCurrencyWithTheLowestValue();

        return "Waluta z najnizszym kursem kupna w podanym dniu to: " +
                currency;
    }
}
