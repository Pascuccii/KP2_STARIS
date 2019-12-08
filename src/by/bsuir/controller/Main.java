package by.bsuir.controller;

import by.bsuir.domain.builder.impl.PaidStudentBuilderImpl;
import by.bsuir.domain.entity.EducationForm;
import by.bsuir.domain.entity.Student;
import by.bsuir.domain.service.exception.ServiceException;
import by.bsuir.domain.service.impl.StudentServiceImpl;
import by.bsuir.view.Printer;

import java.util.List;

public class Main {

    //предметная область "Разработка автоматизированной системы учета документооборота предприятия"
    public static void main(String[] args) {
        // write your code here

        String xsdFilePath = "resources/schema.xsd";
        String xmlFilePath = "resources/workflow.xml";
        StudentServiceImpl service = StudentServiceImpl.getInstance();

        ///

        Student student = new PaidStudentBuilderImpl("ID-6")
                .withName("Глеб")
                .withSurname("Скачко")
                .withPatronymic("Дмитриевич")
                .withEmail("skachko42@gmail.com")
                .withAverageRate(5)
                .withEducationForm(EducationForm.BUDGET)
                .build();


        try {
            Printer.print("Добавление студента:");
            Printer.print(student);
            service.addNewStudent(xmlFilePath, student);
        } catch (ServiceException e) {
            Printer.print(e.getMessage());
        }

        ///////////////////////////////////////////////
        try {
            Printer.print("\nУдаление студента с ID-6...");
            service.deleteStudent(xmlFilePath, "ID-6");
        } catch (ServiceException e) {
            Printer.print(e.getMessage());
        }

        ///////////////////////////////////////////////
        try {
            Printer.print("\nПоиск студента с формой обучения = \"бюджетная\" и средним баллом = 5");
            service.searchByEducationFromAndAverageRate(xmlFilePath, EducationForm.BUDGET,
                    5).forEach(System.out::println);
        } catch (ServiceException e) {
            Printer.print(e.getMessage());
        }

        ///////////////////////////////////////////////
        Printer.print("\n\n\n\nПарсинг SAX-парсером:");
        String parserName = "sax";
        try {
            List<Student> documents = service.getStudentsFromXml(xmlFilePath, xsdFilePath, parserName);
            documents.forEach(Printer::print);
        } catch (ServiceException e) {
            Printer.print(e.getMessage());
        }


        ///////////////////////////////////////////////

        Printer.print("\nПарсинг DOM-парсером:");
        parserName = "dom";
        try {
            List<Student> documents = service.getStudentsFromXml(xmlFilePath, xsdFilePath, parserName);
            documents.forEach(Printer::print);
        } catch (ServiceException e) {
            Printer.print(e.getMessage());
        }

    }
}
