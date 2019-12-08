package by.bsuir.domain.parser.impl;

import by.bsuir.domain.builder.impl.PaidStudentBuilderImpl;
import by.bsuir.domain.entity.EducationForm;
import by.bsuir.domain.entity.Student;
import by.bsuir.domain.parser.XmlParser;
import by.bsuir.domain.parser.exception.ParserException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DomParser implements XmlParser {
    private List<Student> students;

    @Override
    public List<Student> parse(InputStream xmlInputStream) throws ParserException {

        this.students = new ArrayList<>();

        String tagName = "student";

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            javax.xml.parsers.DocumentBuilder builder = factory.newDocumentBuilder();

            org.w3c.dom.Document document = builder.parse(xmlInputStream);

            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName(tagName);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                parseNode(node);
            }

            return students;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new ParserException(e);
        }
    }


    private void parseNode(Node node) {
        Element element = (Element) node;
        //////////////////////
        String id = getId(element);
        String name = getName(element);
        String surname = getSurname(element);
        String patronymic = getPatronymic(element);
        String email = getEmail(element);
        String educationForm = getEducationForm(element);
        int averageRate = getAverageRate(element);


        ///////////////////////

        Student document = new PaidStudentBuilderImpl(id)
                .withName(name)
                .withSurname(surname)
                .withPatronymic(patronymic)
                .withEmail(email)
                .withAverageRate(averageRate)
                .withEducationForm((educationForm.equals("бюджетная") ? EducationForm.BUDGET : EducationForm.PAID))
                .build();
        ///////////////////////

        this.students.add(document);
    }


    private String getElement(final Element element, final String name) {
        return element.getElementsByTagName(name)
                .item(0)
                .getTextContent();
    }


    private String getId(final Element element) {
        return element.getAttribute("id");
    }

    private String getName(Element element) {
        return getElement(element, "name");
    }

    private String getSurname(Element element) {
        return getElement(element, "surname");
    }

    private String getPatronymic(Element element) {
        return getElement(element, "patronymic");
    }

    private String getEmail(Element element) {
        return getElement(element, "email");
    }

    private String getEducationForm(Element element) {
        return getElement(element, "educationForm");
    }

    private int getAverageRate(Element element) {
        return Integer.parseInt(getElement(element, "averageRate"));
    }


    //////////////////////////////////////


    public void addNewStudent(String filePath, Student document1) throws ParserException {
        try {
            javax.xml.parsers.DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            org.w3c.dom.Document document = documentBuilder.parse(filePath);
            // Получаем корневой элемент
            preparedNewStudentElement(document1, document, filePath);
        } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
            throw new ParserException(e);
        }
    }

    private void preparedNewStudentElement(Student studentElement,
                                           org.w3c.dom.Document student,
                                           String filePath) throws TransformerException, FileNotFoundException {
        Node root = student.getDocumentElement();
        //
        Element newStudent = student.createElement("student");
        newStudent.setAttribute("id", studentElement.getId());
        // <documentType>
        Element name = student.createElement("name");
        name.setTextContent(studentElement.getName());
        // <documentName>
        Element surname = student.createElement("surname");
        surname.setTextContent(studentElement.getSurname());
        // <documentAuthor>
        Element patronymic = student.createElement("patronymic");
        patronymic.setTextContent(studentElement.getPatronymic());
        // <documentAddress>
        Element email = student.createElement("email");
        email.setTextContent(String.valueOf(studentElement.getEmail()));
        //documentShelfLife
        Element averageRate = student.createElement("averageRate");
        averageRate.setTextContent(String.valueOf(studentElement.getAverageRate()));

        Element educationForm = student.createElement("educationForm");
        educationForm.setTextContent((studentElement.getEducationForm() == EducationForm.BUDGET) ? "бюджетная" : "платная");
        //
        // Добавляем внутренние элементы
        newStudent.appendChild(name);
        newStudent.appendChild(surname);
        newStudent.appendChild(patronymic);
        newStudent.appendChild(email);
        newStudent.appendChild(averageRate);
        newStudent.appendChild(educationForm);
        // Добавляем student в корневой элемент
        root.appendChild(newStudent);
        // Записываем XML в файл
        writeDocument(student, filePath);
    }


    // Функция для сохранения DOM в файл
    private void writeDocument(org.w3c.dom.Document document, String filePath) throws TransformerException, FileNotFoundException {
        Transformer tr = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        FileOutputStream fos = new FileOutputStream(filePath);
        StreamResult result = new StreamResult(fos);
        tr.transform(source, result);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////

    public void deleteStudentFromXml(String filePath, String studentId) throws ParserException {

        try {
            String tagName = "student";
            InputStream inputStream = new FileInputStream(filePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
            org.w3c.dom.Document document = db.parse(inputStream);

            NodeList nodeList = document.getElementsByTagName(tagName);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Element element = (Element) node;
                if (getId(element).equals(studentId.trim())) {
                    Node parent = element.getParentNode();
                    parent.removeChild(element);
                    parent.normalize();
                }
            }

            writeDocument(document, filePath);
        } catch (ParserConfigurationException | TransformerException | SAXException | IOException e) {
            throw new ParserException(e);
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////

    public List<Student> getStudentsWithEducationFormAndAverageRate(String filePath,
                                                                    EducationForm educationForm,
                                                                    int averageRate) throws ParserException {
        this.students = new ArrayList<>();
        try {
            javax.xml.parsers.DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            org.w3c.dom.Document document = documentBuilder.parse(filePath);
            doGetStudentsWithEducationFormAndAverageRate(document, educationForm, averageRate);
        } catch (ParserConfigurationException | XPathExpressionException | IOException | SAXException e) {
            throw new ParserException(e);
        }
        return students;
    }

    private void doGetStudentsWithEducationFormAndAverageRate(org.w3c.dom.Document document,
                                                              EducationForm educationForm,
                                                              int averageRate) throws XPathExpressionException {
        XPathFactory pathFactory = XPathFactory.newInstance();
        XPath xpath = pathFactory.newXPath();
        String educationFormString = (educationForm == EducationForm.BUDGET) ? "бюджетная" : "платная";
        String query = "students/student[educationForm='" + educationFormString + "' " +
                "and averageRate='" + averageRate + "']";
        ////////
        XPathExpression expr = xpath.compile(query);
        NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            parseNode(node);
        }
    }
}

