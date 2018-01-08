package Parser;

import WebService.IStrategy;

import java.io.IOException;

public abstract class Parser {

    protected IStrategy strategy;

    public abstract void parse(String[] args);

    public IStrategy getStrategy() {
        return strategy;
    }
}
