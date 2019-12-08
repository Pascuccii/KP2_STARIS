package by.bsuir.domain.entity;

public class PaidStudent extends Student {
    public PaidStudent() {
    }

    public PaidStudent(String id) {
        super(id);
    }

    public PaidStudent(String id, String name, String surname, String patronymic, String email, EducationForm educationForm, int averageRate) {
        super(id, name, surname, patronymic, email, educationForm, averageRate);
    }
}
