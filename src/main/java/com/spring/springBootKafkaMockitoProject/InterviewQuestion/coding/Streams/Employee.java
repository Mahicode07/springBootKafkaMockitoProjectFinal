package com.spring.springBootKafkaMockitoProject.InterviewQuestion.coding.Streams;

import lombok.Generated;

public class Employee {

    private int id;
    private String name;
    private double salary;

    @Generated
    public void setId(final int id) {
        this.id = id;
    }

    @Generated
    public void setName(final String name) {
        this.name = name;
    }

    @Generated
    public void setSalary(final double salary) {
        this.salary = salary;
    }

    @Generated
    public int getId() {
        return this.id;
    }

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public double getSalary() {
        return this.salary;
    }

    @Generated
    public Employee(final int id, final String name, final double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Generated
    public String toString() {
        int var10000 = this.getId();
        return "Employee(id=" + var10000 + ", name=" + this.getName() + ", salary=" + this.getSalary() + ")";
    }
}
