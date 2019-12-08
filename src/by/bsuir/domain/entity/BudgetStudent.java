package by.bsuir.domain.entity;

public class BudgetStudent extends Student {
    private int scholarship;

    public BudgetStudent() {
    }

    public BudgetStudent(String id) {
        super(id);
    }

    public BudgetStudent(String id, String name, String surname, String patronymic, String email, EducationForm educationForm, int averageRate, int scholarship) {
        super(id, name, surname, patronymic, email, educationForm, averageRate);
        this.scholarship = scholarship;
    }

    public int getScholarship() {
        return scholarship;
    }

    public void setScholarship(int scholarship) {
        this.scholarship = scholarship;
    }
}
