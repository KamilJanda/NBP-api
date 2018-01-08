package WebService;

public abstract class WebService {

    protected IStrategy strategy;
    //protected Map<String,ParsedObject> parsedObjectsMap;

    WebService(IStrategy IStrategy)
    {
        this.strategy = IStrategy;
        //this.parsedObjectsMap=new HashMap<String, ParsedObject>();
    }


    public String print() {
        return strategy.execute();
    }

    public IStrategy getStrategy() {
        return strategy;
    }
}
