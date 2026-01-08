package com.onehub.employee.service;

import com.onehub.employee.dto.CreateEmployeeRequest;
import com.onehub.employee.dto.EmployeeResponse;
import com.onehub.employee.exception.DuplicateEmailException;
import com.onehub.employee.mapper.EmployeeMapper;
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

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeService employeeService;

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

        Employee employee = Employee.builder()
                .email(request.email())
                .firstname(request.firstname())
                .surname(request.surname())
                .build();

        when(employeeRepository.existsByEmail(request.email())).thenReturn(false);
        when(employeeMapper.toEntity(request)).thenReturn(employee);
        when(employeeRepository.save(any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(employeeMapper.toResponse(any(Employee.class)))
                .thenReturn(new EmployeeResponse(
                        "EMP-123",
                        "Mr",
                        "John",
                        "Doe",
                        LocalDate.of(1990, 1, 1),
                        "Male",
                        "john.doe@test.com",
                        "123 Street"
                ));

        EmployeeResponse result = employeeService.createEmployee(request);

        assertThat(result).isNotNull();
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        CreateEmployeeRequest request = new CreateEmployeeRequest(
            "Mr",
            "Jane",
            "Doe",
            LocalDate.of(1992, 1, 1),
            "Female",
            "existing@test.com",
            "456 Street"
        );

        when(employeeRepository.existsByEmail(request.email())).thenReturn(true);

        assertThrows(DuplicateEmailException.class,
                () -> employeeService.createEmployee(request));

        verify(employeeRepository, never()).save(any());
        verify(employeeMapper, never()).toEntity(any());
    }
}
