package by.bsuir.domain.validator.exception;

public class XmlValidationException extends Exception {
    public XmlValidationException() {
        super();
    }

    public XmlValidationException(String message) {
        super(message);
    }

    public XmlValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public XmlValidationException(Throwable cause) {
        super(cause);
    }
}
