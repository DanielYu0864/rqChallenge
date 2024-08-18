package com.example.rqchallenge.employees.response;

import com.example.rqchallenge.employees.model.Employee;

public class GetEmployeeResponse extends DummyAPIResponse<Employee> {

    private Employee data;

    @Override
    public Employee getData() {
        return data;
    }

    @Override
    public void setData(Employee data) {
        this.data = data;
    }
}
