package com.example.employeedetails.models;

public class EmployeeModel {

    private String name;
    private String age;

    private String salary;

    public EmployeeModel(String name, String age, String salary) {
        this.age = age;
        this.salary = salary;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getSalary() {
        return salary;
    }

}
