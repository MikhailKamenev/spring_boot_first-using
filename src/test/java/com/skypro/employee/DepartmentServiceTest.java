package com.skypro.employee;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import com.skypro.employee.service.DepartmentService;
import com.skypro.employee.service.EmployeeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    private final List<Employee>employees = List.of(new Employee("TestOne", "TestOne", "TestOne", 1, 10000),
            new Employee("TestTwo", "TestTwo", "TestTwo", 2, 11000),
            new Employee("TestThree", "TestThree", "TestThree", 2, 13000),
            new Employee("TestFour", "TestFour", "TestFour", 1, 12000));
    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        Mockito.when(employeeService.getAllEmployees())
                .thenReturn(employees);
    }
    @Test
    void getDepartmentEmployees() {
        Collection<Employee> employeeList = this.departmentService.getDepartmentEmployees(1);
        Assertions.assertThat(employeeList).hasSize(2)
                .contains(employees.get(0),employees.get(3));
    }

    @Test
    void getTotalDepartmentSalary() {
        int sum = this.departmentService.getTotalDepartmentSalary(1);
        Assertions.assertThat(sum).isEqualTo(22000);
    }

    @Test
    void getMaxDepartmentSalary() {
        int maxSalary = this.departmentService.getMaxDepartmentSalary(2);
        Assertions.assertThat(maxSalary).isEqualTo(13000);
    }
    @Test
    void getMinDepartmentSalary() {
        int minSalary = this.departmentService.getMinDepartmentSalary(2);
        Assertions.assertThat(minSalary).isEqualTo(11000);
    }

    @Test
    void groupedEmployees() {
        Map<Integer,List<Employee>>groupedEmployees = this.departmentService.getEmployeesByDepartments();
        Assertions.assertThat(groupedEmployees).hasSize(2)
                .containsKeys(1, 2).containsEntry(1, List.of(employees.get(0), employees.get(3)))
                .containsEntry(2, List.of(employees.get(1), employees.get(2)));
    }
}
