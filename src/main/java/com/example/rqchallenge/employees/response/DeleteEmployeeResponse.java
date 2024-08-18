package com.example.rqchallenge.employees.response;

public class DeleteEmployeeResponse extends DummyAPIResponse<String> {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
