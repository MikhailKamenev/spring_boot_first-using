package com.skypro.employee.model;

public class Employee {
    private final String name;
    private final String patronymic;
    private final String surname;
    private int department;
    private int wage;
    private static int num;
    private int id;

    public Employee(String name, String patronymic, String surname, int department, int wage) {
        if (name != null || !name.isEmpty() || !name.isBlank()) {
            this.name = name;
        } else {
            this.name = "Дефолт";
        }
        if (patronymic != null || !patronymic.isEmpty() || !patronymic.isBlank()) {
            this.patronymic = patronymic;
        } else {
            this.patronymic = "Дефолт";
        }
        if (surname != null || !surname.isEmpty() || !surname.isBlank()) {
            this.surname = surname;
        } else {
            this.surname = "Дефолт";
        }
        if (department > 0 && department <= 5) {
            this.department = department;
        } else {
            this.department= 10;
        }
        if (wage > 0) {
            this.wage = wage;
        } else {
            this.wage = 1;
        }
        this.id = num++;
    }
    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public int getDepartment() {
        return department;
    }

    public int getWage() {
        return wage;
    }

    public int getId() {
        return id;
    }


    public void setDepartment(int department) {
        if (department > 0 && department < 5) {
            this.department = department;
        } else {
        }
    }

    public void setWage(int wage) {
        if (wage > 0) {
            this.wage = wage;
        } else {

        }
    }

    public String toString() {
        return "(" +
                "Имя - " + getName() + " " + getPatronymic() + " " + getSurname() +
                ". Департамент - " + getDepartment() +
                ". Оклад - " + getWage() + ")";
    }

    public String getFullName() {
        return getName() + " " + getPatronymic() + " " + getSurname();
    }
}
