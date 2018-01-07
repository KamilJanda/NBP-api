package WebService;

public class Rates {

    private String country; //– country name
    private String symbol; //– currency symbol (numerical, concerns historic exchange rates)
    private String currency; //– currency name
    private String code; //– currency code
    private String bid; //– calculated currency buy exchange rate (concerns table C) (for table C only)
    private String ask; //– calculated currency sell exchange rate (concerns table C) ()
    private String mid; //– calculated currency average exchange rate (for tables A and B)

    public Rates() {
    }

    public String getCountry() {
        return country;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCode() {
        return code;
    }

    public String getBid() {
        return bid;
    }

    public String getAsk() {
        return ask;
    }

    public String getMid() {
        return mid;
    }
}
