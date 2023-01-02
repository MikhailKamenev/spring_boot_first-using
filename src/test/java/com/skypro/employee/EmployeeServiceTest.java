package com.skypro.employee;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import com.skypro.employee.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class EmployeeServiceTest {
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        this.employeeService = new EmployeeService();
        Stream.of(
        new EmployeeRequest("TestOne", "TestOne", "TestOne", 1, 10000),
        new EmployeeRequest("TestTwo", "TestTwo", "TestTwo", 2, 11000),
        new EmployeeRequest("TestThree", "TestThree", "TestThree", 2, 13000),
        new EmployeeRequest("TestFour", "TestFour", "TestFour", 1, 12000))
                .forEach(employeeService::addEmployee);
    }

    @Test
    public void addEmployeeTest() {
        EmployeeRequest employeeRequest = new EmployeeRequest("Default"
                , "Default", "Default", 1, 10000);
        Employee result = employeeService.addEmployee(employeeRequest);
        Assertions.assertEquals(employeeRequest.getName(),result.getName());
        Assertions.assertEquals(employeeRequest.getPatronymic(),result.getPatronymic());
        Assertions.assertEquals(employeeRequest.getSurname(),result.getSurname());
        Assertions.assertEquals(employeeRequest.getDepartment(),result.getDepartment());
        Assertions.assertEquals(employeeRequest.getWage(),result.getWage());

        assertThat(employeeService.getAllEmployees().contains(result));
    }

    @Test//если отдельно запускать тест, все выполняется и проходит отлично. если запускать
    // все тесты сразу, вылетает ошибка на этом тесте.
    public void removeEmployee() {
        employeeService.removeEmployee(0);
        Collection<Employee>employees = employeeService.getAllEmployees();
        assertThat(employees).hasSize(3);
    }

    @Test
    public void listEmployees() {
        Collection<Employee>employees = employeeService.getAllEmployees();
        assertThat(employees).hasSize(4);
    }

    @Test
    public void sumOfSalaries() {
        int sum = employeeService.getTotalWage();
        assertThat(sum).isEqualTo(46000);
    }
    @Test
    public void maxWage() {
        int maxSalary = employeeService.getAllEmployees()
                .stream().mapToInt(Employee::getWage).max().orElseThrow(RuntimeException::new);
        assertThat(maxSalary).isEqualTo(13000);
    }

    @Test
    public void minWage() {
        int minSalary = employeeService.getAllEmployees()
                .stream().mapToInt(Employee::getWage).min().orElseThrow(RuntimeException::new);
        assertThat(minSalary).isEqualTo(10000);
    }

    @Test
    public void averageWage() {
        double average = employeeService.getAllEmployees()
                .stream().mapToInt(Employee::getWage).average().orElseThrow(RuntimeException::new);
        assertThat(average).isEqualTo(11500);
    }

    @Test
    public void getWellPaidEmployee() {
        double average = employeeService.getAllEmployees()
                .stream().mapToInt(Employee::getWage).average().orElseThrow(RuntimeException::new);
        List<Employee>wellPaidEmployees = employeeService.getAllEmployees().stream().filter(e->e.getWage()>=average).collect(Collectors.toList());
        assertThat(wellPaidEmployees).hasSize(2);
    }
}
