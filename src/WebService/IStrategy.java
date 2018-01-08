package WebService;

public interface IStrategy {

    String makeQueryToServiceApi(String url);

    void createJsonObject();

    String execute();

}
