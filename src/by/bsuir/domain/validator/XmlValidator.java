package by.bsuir.domain.validator;

import by.bsuir.domain.validator.exception.XmlValidationException;
import by.bsuir.view.Printer;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.io.InputStream;

public class XmlValidator {

    public boolean validate(final InputStream xmlInputStream,
                            final InputStream xsdInputStream)
            throws XmlValidationException {
        final String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        final SchemaFactory schemaFactory = SchemaFactory.newInstance(language);

        Printer.print("XML validation started!..");
        try {
            Source schemaSource = new StreamSource(xsdInputStream);
            Source xmlFile = new StreamSource(xmlInputStream);
            Schema schema = schemaFactory.newSchema(schemaSource);
            javax.xml.validation.Validator validator = schema.newValidator();
            validator.validate(xmlFile);

            Printer.print("Validation ended.Xml file is correct.\n");
            return true;
        } catch (IOException e) {
            throw new XmlValidationException("VALIDATION EXCEPTION: I/O error: " + e.getMessage());
        } catch (SAXException e) {
            throw new XmlValidationException("VALIDATION EXCEPTION: SAX error: " + e.getMessage());
        }
    }
}
