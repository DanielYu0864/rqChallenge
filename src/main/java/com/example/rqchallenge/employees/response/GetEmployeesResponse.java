package com.example.rqchallenge.employees.response;

import com.example.rqchallenge.employees.model.Employee;

import java.util.List;

public class GetEmployeesResponse extends DummyAPIResponse<List<Employee>> {

    private List<Employee> data;

    @Override
    public List<Employee> getData() {
        return data;
    }

    @Override
    public void setData(List<Employee> data) {
        this.data = data;
    }

}
