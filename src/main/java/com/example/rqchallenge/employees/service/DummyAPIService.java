package com.example.rqchallenge.employees.service;

import com.example.rqchallenge.employees.exception.ApiException;
import com.example.rqchallenge.employees.request.CreateEmployeeRequest;
import com.example.rqchallenge.employees.response.CreateEmployeeResponse;
import com.example.rqchallenge.employees.response.DeleteEmployeeResponse;
import com.example.rqchallenge.employees.response.GetEmployeeResponse;
import com.example.rqchallenge.employees.response.GetEmployeesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.example.rqchallenge.employees.constent.DummyAPIEndpoint.DUMMY_BASE_URL;

@Service
public class DummyAPIService implements IDummyAPIService{


    private final HttpClient client;
    private final ObjectMapper objectMapper;

    public DummyAPIService() {
        this.client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public GetEmployeesResponse getAllEmployees() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(DUMMY_BASE_URL + "/employees"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == HttpStatus.OK.value())
            return objectMapper.readValue(response.body(), GetEmployeesResponse.class);

        throw new ApiException("Failed to fetch employees. Status code: " + response.statusCode() + " Message: " + response.body());
    }

    @Override
    public GetEmployeeResponse getEmployeeById(String id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(DUMMY_BASE_URL + "/employee/" + id))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == HttpStatus.OK.value())
            return objectMapper.readValue(response.body(), GetEmployeeResponse.class);

        throw new ApiException("Failed to find employee. Status code: " + response.statusCode() + " Message: " + response.body());
    }

    @Override
    public CreateEmployeeResponse createEmployee(CreateEmployeeRequest employee) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(DUMMY_BASE_URL + "/create"))
                .POST(HttpRequest.BodyPublishers.ofString(String.valueOf(employee)))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == HttpStatus.OK.value())
            return objectMapper.readValue(response.body(), CreateEmployeeResponse.class);

        throw new ApiException("Failed to create employee. Status code: " + response.statusCode() + ", Message: " + response.body());

    }

    @Override
    public DeleteEmployeeResponse deleteEmployee(String id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(DUMMY_BASE_URL + "/delete/" + id))
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200)
            return objectMapper.readValue(response.body(), DeleteEmployeeResponse.class);

        throw new ApiException("Failed to delete employee. Status code: " + response.statusCode() + ", Message: " + response.body());

    }
}
