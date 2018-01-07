package WebService;

public class NBPCurrency extends ParsedObject{

    private String table; // typ tabeli
    private String no; // numer tabeli
    private String tradingDate; // data notowania (dotyczy tabeli C)
    private String effectiveDate; // data publikacji
    private String rates; // lista kursów poszczególnych walut w tabeli
    private String country; // nazwa kraju
    private String symbol; // symbol waluty (numeryczny, dotyczy kursów archiwalnych)
    private String currency; // nazwa waluty
    private String code; // kod waluty
    private String bid; // przeliczony kurs kupna waluty (dotyczy tabeli C)
    private String ask; // przeliczony kurs sprzedaży waluty (dotyczy tabeli C)
    private String mid; // przeliczony kurs średni waluty (dotyczy tabel A oraz B)

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

    public String getRates() {
        return rates;
    }

    public void setRates(String rates) {
        this.rates = rates;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }
}
