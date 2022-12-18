package com.skypro.employee.controller;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import com.skypro.employee.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.OptionalInt;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees() {
        return this.employeeService.getAllEmployees();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return this.employeeService.addEmployee(employeeRequest);
    }

    @GetMapping("/employees/wage/totalSum")
    public int getTotalWage() {
        return this.employeeService.getTotalWage();
    }

    @GetMapping("/employees/wage/minWage")
    public Object getMinWage() {
        return this.employeeService.getMinWage();
    }

    @GetMapping("/employees/wage/maxWage")
    public Object getMaxWage() {
        return this.employeeService.getMaxWage();
    }

    @GetMapping("/employees/wage/getWellPaidEmployees")
    public Collection<Employee> getWellPaidEmployees() {
        return this.employeeService.getWellPaidEmployees();
    }
}
