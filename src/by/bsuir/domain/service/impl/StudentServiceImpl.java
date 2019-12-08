package by.bsuir.domain.service.impl;

import by.bsuir.domain.entity.EducationForm;
import by.bsuir.domain.entity.Student;
import by.bsuir.domain.parser.XmlParser;
import by.bsuir.domain.parser.exception.ParserException;
import by.bsuir.domain.parser.factory.ParserFactory;
import by.bsuir.domain.parser.impl.DomParser;
import by.bsuir.domain.service.StudentService;
import by.bsuir.domain.service.exception.ServiceException;
import by.bsuir.domain.validator.XmlValidator;
import by.bsuir.domain.validator.exception.XmlValidationException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private static StudentServiceImpl instance = new StudentServiceImpl();

    public static StudentServiceImpl getInstance() {
        return instance;
    }

    private StudentServiceImpl() {

    }

    private XmlValidator validator = new XmlValidator();

    public List<Student> getStudentsFromXml(String xmlFilePath,
                                            String xsdFilePath,
                                            String parserName) throws ServiceException {

        try (InputStream xmlInputStream = new FileInputStream(xmlFilePath);
             InputStream validationXmlInputStream = new FileInputStream(xmlFilePath);
             InputStream xsdInputStream = new FileInputStream(xsdFilePath)) {


            if (validator.validate(validationXmlInputStream, xsdInputStream)) {

                XmlParser parser = ParserFactory.getInstance().getParser(parserName);
                if (parser != null) {
                    try {
                        return parser.parse(xmlInputStream);
                    } catch (ParserException e) {
                        throw new ServiceException(e);
                    }
                }
            }

            throw new ServiceException("bad parser name!");
        } catch (IOException | XmlValidationException e) {
            throw new ServiceException(e);
        }
    }

    public void addNewStudent(String filePath, Student student) throws ServiceException {
        DomParser domParser = new DomParser();

        try {
            domParser.addNewStudent(filePath, student);
        } catch (ParserException e) {
            throw new ServiceException(e);
        }

    }

    public void deleteStudent(String filePath, String documentId) throws ServiceException {
        DomParser domParser = new DomParser();

        try {
            domParser.deleteStudentFromXml(filePath, documentId);
        } catch (ParserException e) {
            throw new ServiceException(e);
        }
    }


    public List<Student> searchByEducationFromAndAverageRate(String filePath,
                                                             EducationForm educationForm,
                                                             int averageRate) throws ServiceException {
        DomParser domParser = new DomParser();

        try {
            return domParser.getStudentsWithEducationFormAndAverageRate(filePath, educationForm, averageRate);
        } catch (ParserException e) {
            throw new ServiceException(e);
        }
    }
}
