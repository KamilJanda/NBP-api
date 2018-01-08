package Parser;

class ParserCreator {

    Parser create(String parserType)
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
