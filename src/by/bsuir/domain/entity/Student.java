package by.bsuir.domain.entity;

import java.util.Objects;

public abstract class Student {

    private String id;
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private EducationForm educationForm;
    private int averageRate;

    public Student() {
    }

    public Student(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", email='" + email + '\'' +
                ", educationForm=" + educationForm +
                ", averageRate=" + averageRate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return averageRate == student.averageRate &&
                Objects.equals(id, student.id) &&
                Objects.equals(name, student.name) &&
                Objects.equals(surname, student.surname) &&
                Objects.equals(patronymic, student.patronymic) &&
                Objects.equals(email, student.email) &&
                educationForm == student.educationForm;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, patronymic, email, educationForm, averageRate);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(int averageRate) {
        this.averageRate = averageRate;
    }

    public Student(String id, String name, String surname, String patronymic, String email, EducationForm educationForm, int averageRate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.email = email;
        this.educationForm = educationForm;
        this.averageRate = averageRate;
    }

    public EducationForm getEducationForm() {
        return educationForm;
    }

    public void setEducationForm(EducationForm educationForm) {
        this.educationForm = educationForm;
    }
}
