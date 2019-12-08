package by.bsuir.domain.parser.impl;

import by.bsuir.domain.entity.Student;
import by.bsuir.domain.parser.XmlParser;
import by.bsuir.domain.parser.exception.ParserException;
import by.bsuir.domain.parser.handler.StudentHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SaxParser implements XmlParser {


    @Override
    public List<Student> parse(InputStream inputStream) throws ParserException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            StudentHandler studentHandler = new StudentHandler();
            parser.parse(inputStream, studentHandler);
            return studentHandler.getStudents();
        } catch (SAXException | ParserConfigurationException | IOException e) {
            throw new ParserException(e);
        }
    }


}
