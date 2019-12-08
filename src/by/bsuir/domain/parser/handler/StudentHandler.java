package by.bsuir.domain.parser.handler;

import by.bsuir.domain.builder.StudentBuilder;
import by.bsuir.domain.builder.impl.PaidStudentBuilderImpl;
import by.bsuir.domain.entity.EducationForm;
import by.bsuir.domain.entity.Student;
import by.bsuir.domain.entity.StudentParameter;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class StudentHandler extends DefaultHandler {

    private List<Student> students = new ArrayList<>();
    private StudentBuilder studentBuilder;
    private StringBuilder data = new StringBuilder();
    private StudentParameter currentParameter;

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        data = new StringBuilder();
        switch (qName) {
            case "student": {
                String id = attributes.getValue("id");
                studentBuilder = new PaidStudentBuilderImpl(id);
            }
            case "name":
                currentParameter = StudentParameter.STUDENT_NAME;
                break;
            case "surname":
                currentParameter = StudentParameter.STUDENT_SURNAME;
                break;
            case "patronymic":
                currentParameter = StudentParameter.STUDENT_PATRONYMIC;
                break;
            case "email":
                currentParameter = StudentParameter.STUDENT_EMAIL;
                break;
            case "averageRate":
                currentParameter = StudentParameter.STUDENT_AVERAGE_RATE;
                break;
            case "educationForm":
                currentParameter = StudentParameter.STUDENT_EDUCATION_FORM;
                break;
            default:
                break;
        }
    }
    @Override
    public void characters(char[] ch, int start, int length) {
        data.append(ch, start, length);
    }
    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (currentParameter) {
            case STUDENT_NAME:
                studentBuilder.withName(data.toString());
                break;
            case STUDENT_SURNAME:
                studentBuilder.withSurname(data.toString());
                break;
            case STUDENT_PATRONYMIC:
                studentBuilder.withPatronymic(data.toString());
                break;
            case STUDENT_EMAIL:
                studentBuilder.withEmail(data.toString());
                break;
            case STUDENT_AVERAGE_RATE:
                studentBuilder.withAverageRate(Integer.parseInt(data.toString().trim()));
                break;
            case STUDENT_EDUCATION_FORM:
                studentBuilder.withEducationForm((data.toString().trim().equals("бюджетная")) ? EducationForm.BUDGET : EducationForm.PAID);
                break;
            default:
                break;
        }

        createStudent(qName);
    }

    private void createStudent(String qName) {
        if ("student".equals(qName)) {
            Student student = studentBuilder.build();
            this.students.add(student);
        }
    }

}
