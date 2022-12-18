package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private final Map<Integer, Employee>employees=new HashMap<>();

    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        if (employeeRequest.getName() == null
                || employeeRequest.getPatronymic() == null
                || employeeRequest.getSurname() == null) {
            throw new IllegalArgumentException("Employee name should be set");
        }
        Employee employee = new Employee(employeeRequest.getName(), employeeRequest.getPatronymic()
                , employeeRequest.getSurname(),
                employeeRequest.getDepartment(),
                employeeRequest.getWage());
        this.employees.put(employee.getId(), employee);
        return employee;
    }

    public int getTotalWage() {
        return employees.values().stream()
                .mapToInt(Employee::getWage)
                .sum();
    }

    public Object getMinWage() {
        return employees.values().stream().mapToInt(Employee::getWage).min();
    }
    public Object getMaxWage() {
        return employees.values().stream().mapToInt(Employee::getWage).max();
    }
    public Object getMidWage() {
        return employees.values().stream().mapToInt(Employee::getWage).average();
    }

    public Collection<Employee> getWellPaidEmployees() {
        return this.employees.values().stream().filter(s->s.getWage()>employees.values()
                .stream().mapToInt(Employee::getWage).average().getAsDouble()).toList();
    }
}
