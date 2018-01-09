package WebService;

/**
 * Strategy use to find currency with the biggest amplitude form chosen day to today
 */
public class CurrencyWithTheBiggestAmplitude extends NBPStrategy {

    private String startDate;
    private String currency;

    public CurrencyWithTheBiggestAmplitude(String startDate,String currency) {
        this.currency=currency;
        this.startDate=startDate;

        //urlCurrencyQuery="http://api.nbp.pl/api/exchangerates/tables/a/{startDate}/today/?format=json";

    }

    @Override
    public String execute() {
        return null;
    }
}
