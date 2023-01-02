package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    public Collection<Employee>getDepartmentEmployees(int department) {
        return employeeService.getAllEmployees().stream()
                .filter(e->e.getDepartment()==department).collect(Collectors.toList());
    }

    public int getTotalDepartmentSalary(int department) {
        return getDepartmentEmployees(department).stream().mapToInt(Employee::getWage).sum();
    }

    public int getMaxDepartmentSalary(int department) {
        return getDepartmentEmployees(department).stream().mapToInt(Employee::getWage)
                .max().orElseThrow(RuntimeException::new);
    }

    public int getMinDepartmentSalary(int department) {
        return getDepartmentEmployees(department).stream()
                .mapToInt(Employee::getWage).min().orElseThrow(RuntimeException::new);
    }

    public Map<Integer, List<Employee>> getEmployeesByDepartments() {
        return employeeService.getAllEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
