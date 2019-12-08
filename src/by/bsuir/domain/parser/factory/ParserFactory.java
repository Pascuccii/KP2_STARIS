package by.bsuir.domain.parser.factory;

import by.bsuir.domain.parser.XmlParser;
import by.bsuir.domain.parser.impl.DomParser;
import by.bsuir.domain.parser.impl.SaxParser;

public class ParserFactory {

    private static final ParserFactory instance = new ParserFactory();

    public static ParserFactory getInstance() {
        return instance;
    }

    private ParserFactory() {
    }


    public XmlParser getParser(String parserName) {

        switch (parserName.toLowerCase()) {
            case "sax":
                return new SaxParser();
            case "dom":
                return new DomParser();
            default:
                return null;
        }
    }
}
