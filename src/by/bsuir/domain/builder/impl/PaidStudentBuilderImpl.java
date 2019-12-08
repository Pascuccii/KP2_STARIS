package by.bsuir.domain.builder.impl;

import by.bsuir.domain.builder.StudentBuilder;
import by.bsuir.domain.entity.EducationForm;
import by.bsuir.domain.entity.PaidStudent;

public class PaidStudentBuilderImpl implements StudentBuilder {
    private String id;
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private EducationForm educationForm;
    private int averageRate;

    public PaidStudentBuilderImpl(String id) {
        this.id = id;
    }

    @Override
    public StudentBuilder withName(String name) {
        this.name  = name;
        return this;
    }

    @Override
    public StudentBuilder withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    @Override
    public StudentBuilder withPatronymic(String patronymic) {
        this.patronymic = patronymic;
        return this;
    }

    @Override
    public StudentBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public StudentBuilder withAverageRate(int averageRate) {
        this.averageRate = averageRate;
        return this;
    }

    @Override
    public StudentBuilder withEducationForm(EducationForm educationForm) {
        this.educationForm = educationForm;
        return this;
    }

    @Override
    public PaidStudent build() {
        PaidStudent paidStudent = new PaidStudent(id);
        paidStudent.setName(name);
        paidStudent.setSurname(surname);
        paidStudent.setPatronymic(patronymic);
        paidStudent.setEmail(email);
        paidStudent.setEducationForm(educationForm);
        paidStudent.setAverageRate(averageRate);
        //
        return paidStudent;
    }
}
