package WebService;

public class WebServiceFactory {

    public static WebService create(String className,IStrategy strategy)
    {
        WebService webService=null;

        switch (className)
        {
            case "NBP":
                webService=new NBPWebService(strategy);
                break;
            default:
                throw new IllegalArgumentException();
        }

        return webService;
    }

}
