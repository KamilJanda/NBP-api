package WebService;

public class NBPGold extends ParsedObject {

    private String data;
    private Double cena;

    public NBPGold() {
    }

    @Override
    public String toString() {
        return "NBPGold{" +
                "data='" + data + '\'' +
                ", cena='" + cena + '\'' +
                '}';
    }

    public String getData() {
        return data;
    }

    public Double getCena() {
        return cena;
    }
}
