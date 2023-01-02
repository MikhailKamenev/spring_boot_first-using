package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;

@Service
public class EmployeeService {
    private final Map<Integer, Employee>employees=new HashMap<>();

    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        HttpClientErrorException badRequest = new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        if (employeeRequest.getName() == null
                || employeeRequest.getSurname() == null
                ||StringUtils.isEmpty(employeeRequest.getName())
                ||StringUtils.isEmpty(employeeRequest.getSurname())
                ||StringUtils.isBlank(employeeRequest.getSurname())
                ||StringUtils.isBlank(employeeRequest.getSurname())) {
            throw badRequest;
        }
        Employee employee = new Employee(StringUtils.capitalize(employeeRequest.getName()),
                StringUtils.capitalize(employeeRequest.getPatronymic())
                , StringUtils.capitalize(employeeRequest.getSurname()),
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
    public Employee removeEmployee(int id) {
        return employees.remove(id);
    }
}
