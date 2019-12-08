package by.bsuir.domain.parser;

import by.bsuir.domain.entity.Student;
import by.bsuir.domain.parser.exception.ParserException;

import java.io.InputStream;
import java.util.List;

public interface XmlParser {

    List<Student> parse(InputStream inputStream) throws ParserException;

}
