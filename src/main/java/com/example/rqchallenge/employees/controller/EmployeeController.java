package com.example.rqchallenge.employees.controller;

import com.example.rqchallenge.employees.model.Employee;
import com.example.rqchallenge.employees.request.CreateEmployeeRequest;
import com.example.rqchallenge.employees.service.EmployeeService;
import com.example.rqchallenge.employees.service.IEmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class EmployeeController implements IEmployeeController {

    private final IEmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @Override
    public ResponseEntity<List<Employee>> getAllEmployees() throws IOException, InterruptedException {
        return ResponseEntity.ok(employeeService.getAllEmployees());

    }

    @Override
    public ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString) throws IOException, InterruptedException {
        return ResponseEntity.ok(employeeService.getEmployeesByNameSearch(searchString));
    }

    @Override
    public ResponseEntity<Employee> getEmployeeById(String id) throws IOException, InterruptedException {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @Override
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() throws IOException, InterruptedException {
        return ResponseEntity.ok(employeeService.getHighestSalaryOfEmployees());
    }

    @Override
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() throws IOException, InterruptedException {
        return ResponseEntity.ok(employeeService.getTopTenHighestEarningEmployeeNames());
    }

    @Override
    public ResponseEntity<String> createEmployee(CreateEmployeeRequest employeeInput) throws IOException, InterruptedException {

        Employee employee = employeeService.createEmployee(employeeInput);

        String status = "Employee: " + employee.getId() + " create successfully.";

        return ResponseEntity.ok(status);
    }

    @Override
    public ResponseEntity<String> deleteEmployeeById(String id) throws IOException, InterruptedException {
        return ResponseEntity.ok(employeeService.deleteEmployById(id));
    }
}
