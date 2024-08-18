package com.example.rqchallenge.employees.service;

import com.example.rqchallenge.employees.model.Employee;
import com.example.rqchallenge.employees.request.CreateEmployeeRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface IEmployeeService {

    List<Employee> getAllEmployees() throws IOException, InterruptedException;

    List<Employee> getEmployeesByNameSearch(String searchString) throws IOException, InterruptedException;

    Employee getEmployeeById(String id) throws IOException, InterruptedException;

    Integer getHighestSalaryOfEmployees() throws IOException, InterruptedException;

    List<String> getTopTenHighestEarningEmployeeNames() throws IOException, InterruptedException;

    Employee createEmployee(CreateEmployeeRequest createEmployeeRequest) throws IOException, InterruptedException;

    String deleteEmployById(String id) throws IOException, InterruptedException;


}
