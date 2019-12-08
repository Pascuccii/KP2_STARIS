package by.bsuir.domain.builder;

import by.bsuir.domain.entity.EducationForm;
import by.bsuir.domain.entity.Student;

public interface StudentBuilder {

    StudentBuilder withName(String documentType);

    StudentBuilder withSurname(String documentName);

    StudentBuilder withPatronymic(String documentAuthor);

    StudentBuilder withEmail(String documentAddress);

    StudentBuilder withAverageRate(int shelfLife);

    StudentBuilder withEducationForm(EducationForm educationForm);

    Student build();
}
