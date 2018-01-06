package WebService;

abstract class WebService {

    protected Strategy strategy;

    WebService(Strategy strategy)
    {
        this.strategy=strategy;
    }

}
