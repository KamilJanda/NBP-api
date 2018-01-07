package WebService;

public interface Strategy {


    String makeQueryToServiceApi(String url);

    void createJSON();

    void execute();

}
