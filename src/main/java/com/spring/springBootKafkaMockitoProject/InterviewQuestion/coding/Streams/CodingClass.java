//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.spring.springBootKafkaMockitoProject.InterviewQuestion.coding.Streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CodingClass {
    public static void main(String[] args) {
        List<Employee> employeeList = Arrays.asList(new Employee(1, "mahendra", (double)1234.0F), new Employee(2, "pankaj", (double)2000.0F), new Employee(3, "DK", (double)3000.0F), new Employee(4, "jaswant", (double)4000.0F));
        List<Employee> salaryGreaterThan2k = (List)employeeList.stream().filter((employee) -> employee.getSalary() > (double)2000.0F).collect(Collectors.toList());
        System.out.println(salaryGreaterThan2k);
        List<Employee> salary10Hike = (List)employeeList.stream().map((employee) -> {
            double salary = employee.getSalary() * 1.1;
            employee.setSalary(salary);
            return employee;
        }).collect(Collectors.toList());
        System.out.println(salary10Hike);
        List<Employee> salary10HikePeek = employeeList.stream().peek((employee) -> employee.setSalary(employee.getSalary() * 1.1)).toList();
        System.out.println(salary10HikePeek);
        Optional<Employee> highestPaid = employeeList.stream().max(Comparator.comparing(Employee::getSalary));
        System.out.println(highestPaid.get());
        int n = 2;
        Optional<Employee> nthHighestEmployee = employeeList.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).distinct().skip((long)(n - 1)).findFirst();
        System.out.println("Highest salary" + nthHighestEmployee.get());
        List<Employee> sortedEmployees = employeeList.stream().sorted(Comparator.comparing(Employee::getName)).toList();
        System.out.println(sortedEmployees);
        List<Employee> sortedEmployeesDesc = employeeList.stream().sorted(Comparator.comparing(Employee::getName).reversed()).toList();
        System.out.println(sortedEmployeesDesc);
    }
}
