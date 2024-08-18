package com.example.rqchallenge.employees.service;

import com.example.rqchallenge.employees.request.CreateEmployeeRequest;
import com.example.rqchallenge.employees.response.CreateEmployeeResponse;
import com.example.rqchallenge.employees.response.DeleteEmployeeResponse;
import com.example.rqchallenge.employees.response.GetEmployeeResponse;
import com.example.rqchallenge.employees.response.GetEmployeesResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
interface IDummyAPIService {

    GetEmployeesResponse getAllEmployees() throws IOException, InterruptedException;

    GetEmployeeResponse getEmployeeById(String id) throws IOException, InterruptedException;

    CreateEmployeeResponse createEmployee(CreateEmployeeRequest employee) throws IOException, InterruptedException;

    DeleteEmployeeResponse deleteEmployee(String id) throws IOException, InterruptedException;

}
