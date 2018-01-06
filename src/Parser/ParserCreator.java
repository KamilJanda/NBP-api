package Parser;

public class ParserCreator {

    public Parser create(String parserType)
    {
        switch (parserType)
        {
            case "NBP":
                return new NBPParser();
            default:
                throw new IllegalArgumentException();
        }

    }

}
