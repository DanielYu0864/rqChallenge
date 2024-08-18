package com.example.rqchallenge.employees.controller;

import com.example.rqchallenge.employees.model.Employee;
import com.example.rqchallenge.employees.request.CreateEmployeeRequest;
import com.example.rqchallenge.employees.service.EmployeeService;
import com.example.rqchallenge.employees.service.IEmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeController = new EmployeeController( employeeService);
    }

    @Test
    public void testGetAllEmployees() throws IOException, InterruptedException {
        List<Employee> employees = Collections.singletonList(new Employee());
        when(employeeService.getAllEmployees()).thenReturn(employees);

        ResponseEntity<List<Employee>> response = employeeController.getAllEmployees();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(employees, response.getBody());
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    public void testGetEmployeesByNameSearch() throws IOException, InterruptedException {
        List<Employee> employees = Collections.singletonList(new Employee());
        String searchString = "John";
        when(employeeService.getEmployeesByNameSearch(searchString)).thenReturn(employees);

        ResponseEntity<List<Employee>> response = employeeController.getEmployeesByNameSearch(searchString);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(employees, response.getBody());
        verify(employeeService, times(1)).getEmployeesByNameSearch(searchString);
    }

    @Test
    public void testCreateEmployee() throws IOException, InterruptedException {
        CreateEmployeeRequest request = new CreateEmployeeRequest();
        Employee employee = new Employee();
        employee.setId("1");
        when(employeeService.createEmployee(request)).thenReturn(employee);

        ResponseEntity<String> response = employeeController.createEmployee(request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Employee: 1 create successfully.", response.getBody());
        verify(employeeService, times(1)).createEmployee(request);
    }

    @Test
    public void testDeleteEmployeeById() throws IOException, InterruptedException {
        String id = "1";
        when(employeeService.deleteEmployById(id)).thenReturn("Employee deleted");

        ResponseEntity<String> response = employeeController.deleteEmployeeById(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Employee deleted", response.getBody());
        verify(employeeService, times(1)).deleteEmployById(id);
    }


    @Test
    public void testGetHighestSalaryOfEmployees() throws IOException, InterruptedException {
        Integer highestSalary = 1000;
        when(employeeService.getHighestSalaryOfEmployees()).thenReturn(highestSalary);

        ResponseEntity<Integer> response = employeeController.getHighestSalaryOfEmployees();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(highestSalary, response.getBody());
        verify(employeeService, times(1)).getHighestSalaryOfEmployees();
    }

    @Test
    public void testGetTopTenHighestEarningEmployeeNames() throws IOException, InterruptedException {
        List<String> topTenNames = Collections.singletonList("John Doe");
        when(employeeService.getTopTenHighestEarningEmployeeNames()).thenReturn(topTenNames);

        ResponseEntity<List<String>> response = employeeController.getTopTenHighestEarningEmployeeNames();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(topTenNames, response.getBody());
        verify(employeeService, times(1)).getTopTenHighestEarningEmployeeNames();
    }
}