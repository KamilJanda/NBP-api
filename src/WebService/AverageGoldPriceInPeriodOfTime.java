package WebService;

public class AverageGoldPriceInPeriodOfTime extends NBPStrategy{

    private String startDate;
    private String endDate;

    public AverageGoldPriceInPeriodOfTime(String startDate,String endDate)
    {
        this.startDate=startDate;
        this.endDate=endDate;
        urlGoldQuery="http://api.nbp.pl/api/cenyzlota/"+startDate+"/"+endDate+"/?format=json";
    }

    private double countAvgPrice()
    {
        double avg=0;

        for (NBPGold record: nbpGold) {
            avg+=record.getCena();
        }

        avg/=nbpGold.length;

        return avg;
    }

    @Override
    public String execute() {
        createJsonObject();
        double avgPrice=countAvgPrice();

        StringBuilder result=new StringBuilder();

        result.append("Srednia cena w podanym okresie to: ");
        result.append(String.format("%.2f",avgPrice));


        return result.toString();
    }
}
