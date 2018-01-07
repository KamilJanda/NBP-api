package WebService;

import java.util.HashMap;
import java.util.Map;

abstract class WebService {

    protected Strategy strategy;
    protected Map<String,ParsedObject> parsedObjectsMap;

    WebService(Strategy strategy)
    {
        this.strategy=strategy;
        this.parsedObjectsMap=new HashMap<String, ParsedObject>();
    }

}
