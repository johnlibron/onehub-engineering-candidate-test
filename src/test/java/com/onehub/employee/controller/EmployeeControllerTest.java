
package com.onehub.employee.controller;

import com.onehub.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void shouldCreateEmployee() throws Exception {
        when(employeeService.createEmployee(any())).thenReturn(null);
        mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                {
                  "title": "Mr",
                  "firstname": "John",
                  "surname": "Doe",
                  "dob": "1990-01-01",
                  "gender": "Male",
                  "email": "john.doe@test.com",
                  "address": "123 Street"
                }
                """))
                .andExpect(status().isCreated());
    }
}
