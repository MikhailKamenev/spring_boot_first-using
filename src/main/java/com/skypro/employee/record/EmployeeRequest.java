package com.skypro.employee.record;

public class EmployeeRequest {
    private String name;
    private String patronymic;
    private String surname;
    private int department;
    private int wage;

    public EmployeeRequest(String name, String patronymic, String surname, int department, int wage) {
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
        this.department = department;
        this.wage = wage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }
}
