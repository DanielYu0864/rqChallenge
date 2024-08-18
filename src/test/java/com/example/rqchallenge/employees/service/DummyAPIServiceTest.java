package com.example.rqchallenge.employees.service;

import com.example.rqchallenge.employees.exception.ApiException;
import com.example.rqchallenge.employees.request.CreateEmployeeRequest;
import com.example.rqchallenge.employees.response.CreateEmployeeResponse;
import com.example.rqchallenge.employees.response.DeleteEmployeeResponse;
import com.example.rqchallenge.employees.response.GetEmployeeResponse;
import com.example.rqchallenge.employees.response.GetEmployeesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DummyAPIServiceTest {
    @Mock
    private HttpClient httpClient;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private HttpResponse<String> httpResponse;

    @InjectMocks
    private DummyAPIService dummyAPIService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEmployees_Success() throws IOException, InterruptedException {

        when(httpResponse.statusCode()).thenReturn(HttpStatus.OK.value());
        when(httpResponse.body()).thenReturn("{}");
        when(httpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString())))
                .thenReturn(httpResponse);
        when(objectMapper.readValue(any(String.class), any(Class.class))).thenReturn(new GetEmployeesResponse());

        GetEmployeesResponse response = dummyAPIService.getAllEmployees();

        assertNotNull(response);
    }

    @Test
    void getAllEmployees_Failure() throws IOException, InterruptedException {
        when(httpResponse.statusCode()).thenReturn(HttpStatus.INTERNAL_SERVER_ERROR.value());
        when(httpResponse.body()).thenReturn("Error message");
        when(httpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString())))
                .thenReturn(httpResponse);

        Exception exception = assertThrows(ApiException.class, () -> dummyAPIService.getAllEmployees());
        assertTrue(exception.getMessage().contains("Failed to fetch employees"));
    }


    @Test
    void getEmployeeById_Success() throws IOException, InterruptedException {
        when(httpResponse.statusCode()).thenReturn(HttpStatus.OK.value());
        when(httpResponse.body()).thenReturn("{}");
        when(httpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString())))
                .thenReturn(httpResponse);
        when(objectMapper.readValue(any(String.class), any(Class.class))).thenReturn(new GetEmployeeResponse());

        GetEmployeeResponse response = dummyAPIService.getEmployeeById("1");

        assertNotNull(response);
    }


    @Test
    void createEmployee_Success() throws IOException, InterruptedException {
        when(httpResponse.statusCode()).thenReturn(HttpStatus.OK.value());
        when(httpResponse.body()).thenReturn("{}");
        when(httpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString())))
                .thenReturn(httpResponse);
        when(objectMapper.readValue(any(String.class), any(Class.class))).thenReturn(new CreateEmployeeResponse());

        CreateEmployeeResponse response = dummyAPIService.createEmployee(new CreateEmployeeRequest());

        assertNotNull(response);
    }

    @Test
    void deleteEmployee_Success() throws IOException, InterruptedException {
        when(httpResponse.statusCode()).thenReturn(HttpStatus.OK.value());
        when(httpResponse.body()).thenReturn("{}");
        when(httpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString())))
                .thenReturn(httpResponse);
        when(objectMapper.readValue(any(String.class), any(Class.class))).thenReturn(new DeleteEmployeeResponse());

        DeleteEmployeeResponse response = dummyAPIService.deleteEmployee("1");

        assertNotNull(response);
    }
}