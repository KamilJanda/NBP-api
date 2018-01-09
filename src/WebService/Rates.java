package WebService;

public class Rates {

    private String effectiveDate;
    private String country; //– country name
    private String symbol; //– currency symbol (numerical, concerns historic exchange rates)
    private String currency; //– currency name
    private String code; //– currency code
    private Double bid; //– calculated currency buy exchange rate (concerns table C) (for table C only)
    private Double ask; //– calculated currency sell exchange rate (concerns table C) ()
    private Double mid; //– calculated currency average exchange rate (for tables A and B)

    public Rates() {
    }

    public String getEffectiveDate() {
        return effectiveDate;
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

    public Double getBid() {
        return bid;
    }

    public Double getAsk() {
        return ask;
    }

    public Double getMid() {
        return mid;
    }
}
