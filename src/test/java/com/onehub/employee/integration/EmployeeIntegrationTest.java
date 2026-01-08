package com.onehub.employee.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onehub.employee.dto.CreateEmployeeRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldCreateEmployeeSuccessfully() {
        CreateEmployeeRequest request = new CreateEmployeeRequest(
            "Mr",
            "John",
            "Doe",
            LocalDate.of(1990, 1, 1),
            "Male",
            "john.doe@test.com",
            "123 Street"
        );

        ResponseEntity<String> response = restTemplate.postForEntity("/api/employees", request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).contains("EMP-");
        assertThat(response.getBody()).contains("john.doe@test.com");
    }

    @Test
    void shouldRejectDuplicateEmail() {
        CreateEmployeeRequest request = new CreateEmployeeRequest(
            "Mrs",
            "Jane",
            "Doe",
            LocalDate.of(1992, 1, 1),
            "Female",
            "duplicate@test.com",
            "456 Street"
        );

        restTemplate.postForEntity("/api/employees", request, String.class);

        ResponseEntity<String> second = restTemplate.postForEntity("/api/employees", request, String.class);

        assertThat(second.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }
}
