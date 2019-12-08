package by.bsuir.domain.service;

import by.bsuir.domain.entity.EducationForm;
import by.bsuir.domain.entity.Student;
import by.bsuir.domain.service.exception.ServiceException;

import java.util.List;

public interface StudentService {

    List<Student> searchByEducationFromAndAverageRate(String filePath,
                                                      EducationForm educationForm,
                                                      int averageRate) throws ServiceException;

    void deleteStudent(String filePath, String studentId) throws ServiceException;

    void addNewStudent(String filePath, Student document) throws ServiceException;

    List<Student> getStudentsFromXml(String xmlFilePath,
                                     String xsdFilePath,
                                     String parserName) throws ServiceException;
}
