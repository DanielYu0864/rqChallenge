package com.example.rqchallenge.employees.service;

import com.example.rqchallenge.employees.model.Employee;
import com.example.rqchallenge.employees.request.CreateEmployeeRequest;
import com.example.rqchallenge.employees.response.CreateEmployeeResponse;
import com.example.rqchallenge.employees.response.DeleteEmployeeResponse;
import com.example.rqchallenge.employees.response.GetEmployeeResponse;
import com.example.rqchallenge.employees.response.GetEmployeesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    private DummyAPIService dummyAPIService;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        employeeService = new EmployeeService(dummyAPIService);
    }

    @Test
    void getAllEmployees() throws IOException, InterruptedException {
        GetEmployeesResponse mockResponse = new GetEmployeesResponse();
        mockResponse.setData(Arrays.asList(new Employee(), new Employee()));
        when(dummyAPIService.getAllEmployees()).thenReturn(mockResponse);

        List<Employee> result = employeeService.getAllEmployees();

        assertEquals(2, result.size());
        verify(dummyAPIService).getAllEmployees();
    }

    @Test
    void getEmployeesByNameSearch() throws IOException, InterruptedException {
        GetEmployeesResponse mockResponse = new GetEmployeesResponse();
        Employee employee1 = new Employee();
        employee1.setEmployeeName("John Doe");
        Employee employee2 = new Employee();
        employee2.setEmployeeName("Jane Doe");
        mockResponse.setData(Arrays.asList(employee1, employee2));
        when(dummyAPIService.getAllEmployees()).thenReturn(mockResponse);

        List<Employee> result = employeeService.getEmployeesByNameSearch("john doe");

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getEmployeeName());
    }

    @Test
    void getEmployeeById() throws IOException, InterruptedException {
        GetEmployeeResponse mockResponse = new GetEmployeeResponse();
        Employee employee = new Employee();
        employee.setId("1");
        mockResponse.setData(employee);
        when(dummyAPIService.getEmployeeById("1")).thenReturn(mockResponse);

        Employee result = employeeService.getEmployeeById("1");

        assertEquals("1", result.getId());
    }

    @Test
    void getHighestSalaryOfEmployees() throws IOException, InterruptedException {
        GetEmployeesResponse mockResponse = new GetEmployeesResponse();
        Employee employee1 = new Employee();
        employee1.setEmployeeSalary(50000);
        Employee employee2 = new Employee();
        employee2.setEmployeeSalary(60000);
        mockResponse.setData(Arrays.asList(employee1, employee2));
        when(dummyAPIService.getAllEmployees()).thenReturn(mockResponse);

        Integer result = employeeService.getHighestSalaryOfEmployees();

        assertEquals(60000, result);
    }


    @Test
    void getTopTenHighestEarningEmployeeNames() throws IOException, InterruptedException {
        GetEmployeesResponse mockResponse = new GetEmployeesResponse();
        List<Employee> employees = Arrays.asList(
                createEmployee("A", 100000),
                createEmployee("B", 90000),
                createEmployee("C", 80000),
                createEmployee("D", 70000),
                createEmployee("E", 60000),
                createEmployee("F", 50000),
                createEmployee("G", 40000),
                createEmployee("H", 30000),
                createEmployee("I", 20000),
                createEmployee("G", 10000),
                createEmployee("K", 9000)
        );
        mockResponse.setData(employees);
        when(dummyAPIService.getAllEmployees()).thenReturn(mockResponse);

        List<String> result = employeeService.getTopTenHighestEarningEmployeeNames();

        assertEquals(10, result.size());
        assertEquals("A", result.get(0));
        assertEquals("G", result.get(9));
    }

    @Test
    void createEmployee() throws IOException, InterruptedException {
        CreateEmployeeRequest request = new CreateEmployeeRequest();
        CreateEmployeeResponse mockResponse = new CreateEmployeeResponse();
        Employee createdEmployee = new Employee();
        createdEmployee.setId("1");
        mockResponse.setData(createdEmployee);
        when(dummyAPIService.createEmployee(request)).thenReturn(mockResponse);

        Employee result = employeeService.createEmployee(request);

        assertEquals("1", result.getId());
    }

    @Test
    void deleteEmployById() throws IOException, InterruptedException {
        String id = "1";
        GetEmployeeResponse getEmployeeResponse = new GetEmployeeResponse();
        Employee employee = new Employee();
        employee.setEmployeeName("A");
        getEmployeeResponse.setData(employee);
        when(dummyAPIService.getEmployeeById(id)).thenReturn(getEmployeeResponse);

        DeleteEmployeeResponse deleteEmployeeResponse = new DeleteEmployeeResponse();
        deleteEmployeeResponse.setMessage("successfully deleted");
        when(dummyAPIService.deleteEmployee(id)).thenReturn(deleteEmployeeResponse);

        String result = employeeService.deleteEmployById(id);

        assertEquals("Employee: A delete successfully deleted", result);
    }

    private Employee createEmployee(String name, int salary) {
        Employee employee = new Employee();
        employee.setEmployeeName(name);
        employee.setEmployeeSalary(salary);
        return employee;
    }
}