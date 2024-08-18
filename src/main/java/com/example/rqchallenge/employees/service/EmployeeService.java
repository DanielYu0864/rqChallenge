package com.example.rqchallenge.employees.service;

import com.example.rqchallenge.employees.model.Employee;
import com.example.rqchallenge.employees.request.CreateEmployeeRequest;
import com.example.rqchallenge.employees.response.DeleteEmployeeResponse;
import com.example.rqchallenge.employees.response.GetEmployeesResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements IEmployeeService {

    private final IDummyAPIService dummyAPIService;

    public EmployeeService(DummyAPIService dummyAPIService) {
        this.dummyAPIService = dummyAPIService;
    }


    @Override
    public List<Employee> getAllEmployees() throws IOException, InterruptedException {
        return dummyAPIService.getAllEmployees().getData();
    }

    @Override
    public List<Employee> getEmployeesByNameSearch(String name) throws IOException, InterruptedException {
        GetEmployeesResponse response = dummyAPIService.getAllEmployees();
        return response.getData().stream()
                .filter(employee -> employee.getEmployeeName().toLowerCase().equals(name)).collect(Collectors.toList());
    }
    @Override
    public Employee getEmployeeById(String id) throws IOException, InterruptedException {
        return dummyAPIService.getEmployeeById(id).getData();
    }

    @Override
    public Integer getHighestSalaryOfEmployees() throws IOException, InterruptedException {
        GetEmployeesResponse response = dummyAPIService.getAllEmployees();

        Employee employee = response.getData().stream().max(Comparator.comparingInt(Employee::getEmployeeSalary)).orElse(null);

        return employee != null ? employee.getEmployeeSalary() :  0;
    }

    @Override
    public List<String> getTopTenHighestEarningEmployeeNames() throws IOException, InterruptedException {

        GetEmployeesResponse response = dummyAPIService.getAllEmployees();

        List<String> employeeNames = new ArrayList<>();
        List<Employee> employee = response
                .getData().stream()
                .sorted(Comparator.comparingInt(Employee::getEmployeeSalary).reversed())
                .collect(Collectors.toList()).subList(0, 10);

        employee.forEach(e -> employeeNames.add(e.getEmployeeName()));

        return employeeNames;
    }

    @Override
    public Employee createEmployee(CreateEmployeeRequest createEmployeeRequest) throws IOException, InterruptedException {
        return dummyAPIService.createEmployee(createEmployeeRequest).getData();
    }

    @Override
    public String deleteEmployById(String id) throws IOException, InterruptedException {

        String employee = dummyAPIService.getEmployeeById(id).getData().getEmployeeName();

        DeleteEmployeeResponse deleteEmployeeResponse = dummyAPIService.deleteEmployee(id);

        return "Employee: " + employee + " delete " + deleteEmployeeResponse.getMessage();
    }
}
