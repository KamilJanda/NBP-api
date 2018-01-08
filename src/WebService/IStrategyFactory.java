package WebService;


interface IStrategyFactory {
    IStrategy create(String className);
}
