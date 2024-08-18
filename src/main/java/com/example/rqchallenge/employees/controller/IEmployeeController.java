package com.example.rqchallenge.employees.controller;

import com.example.rqchallenge.employees.model.Employee;
import com.example.rqchallenge.employees.request.CreateEmployeeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public interface IEmployeeController {
    /**
     * Handles GET all employees.
     *
     * @return a ResponseEntity containing a list of employees and HTTP status OK
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    @GetMapping()
    ResponseEntity<List<Employee>> getAllEmployees() throws IOException, InterruptedException;
    /**
     * Handles GET employees by name search.
     *
     * @param searchString the name to search for
     * @return a ResponseEntity containing a list of employees matching the search criteria and HTTP status OK
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    @GetMapping("/search/{searchString}")
    ResponseEntity<List<Employee>> getEmployeesByNameSearch(@PathVariable String searchString) throws IOException, InterruptedException;
    /**
     * Handles GET an employee by ID.
     *
     * @param id the ID of the employee to retrieve
     * @return a ResponseEntity containing the employee and HTTP status OK
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    @GetMapping("/{id}")
    ResponseEntity<Employee> getEmployeeById(@PathVariable String id) throws IOException, InterruptedException;
    /**
     * Handles GET the highest salary among all employees.
     *
     * @return a ResponseEntity containing the highest salary and HTTP status OK
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    @GetMapping("/highestSalary")
    ResponseEntity<Integer> getHighestSalaryOfEmployees() throws IOException, InterruptedException;
    /**
     * Handles GET the names of the top ten highest-earning employees.
     *
     * @return a ResponseEntity containing a list of top ten employee names and HTTP status OK
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    @GetMapping("/topTenHighestEarningEmployeeNames")
    ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() throws IOException, InterruptedException;
    /**
     * Handles POST create a new employee.
     *
     * @param employeeInput the request body containing employee details
     * @return a ResponseEntity containing a success message and HTTP status OK
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    @PostMapping()
    ResponseEntity<String> createEmployee(@RequestBody CreateEmployeeRequest employeeInput) throws IOException, InterruptedException;

    /**
     * Handles DELETE employee by ID.
     *
     * @param id the ID of the employee to delete
     * @return a ResponseEntity containing a success message and HTTP status OK
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteEmployeeById(@PathVariable String id) throws IOException, InterruptedException;

}
