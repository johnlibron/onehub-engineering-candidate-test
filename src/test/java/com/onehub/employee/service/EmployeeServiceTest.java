package com.onehub.employee.service;

import com.onehub.employee.dto.CreateEmployeeRequest;
import com.onehub.employee.exception.DuplicateEmailException;
import com.onehub.employee.model.Employee;
import com.onehub.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void shouldCreateEmployeeSuccessfully() {
        CreateEmployeeRequest request = new CreateEmployeeRequest();
        request.setTitle("Mr");
        request.setFirstname("John");
        request.setSurname("Doe");
        request.setDob(LocalDate.of(1990, 1, 1));
        request.setGender("Male");
        request.setEmail("john.doe@test.com");
        request.setAddress("123 Street");

        when(employeeRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(employeeRepository.save(any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Employee result = employeeService.createEmployee(request);

        ArgumentCaptor<Employee> captor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository).save(captor.capture());
        Employee savedEmployee = captor.getValue();
        assertThat(savedEmployee.getEmployeeNo()).startsWith("EMP-");
        assertThat(savedEmployee.getEmail()).isEqualTo("john.doe@test.com");
        assertThat(savedEmployee.getFirstname()).isEqualTo("John");
        assertThat(savedEmployee.getSurname()).isEqualTo("Doe");
        assertThat(result).isNotNull();
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        CreateEmployeeRequest request = new CreateEmployeeRequest();
        request.setEmail("existing@test.com");

        when(employeeRepository.existsByEmail(request.getEmail())).thenReturn(true);

        assertThrows(DuplicateEmailException.class, () -> employeeService.createEmployee(request));

        verify(employeeRepository, never()).save(any());
    }
}
