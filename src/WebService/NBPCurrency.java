package WebService;

public class NBPCurrency extends ParsedObject{

    private String table; // typ tabeli
    private String no; // numer tabeli
    private String tradingDate; // data notowania (dotyczy tabeli C)
    private String effectiveDate; // data publikacji
    private Rates[] rates; // lista kursów poszczególnych walut w tabeli

    public NBPCurrency() {
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTradingDate() {
        return tradingDate;
    }

    public void setTradingDate(String tradingDate) {
        this.tradingDate = tradingDate;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Rates[] getRates() {
        return rates;
    }

    public void setRates(Rates[] rates) {
        this.rates = rates;
    }
}
