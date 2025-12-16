
package com.onehub.employee.service;

import com.onehub.employee.dto.CreateEmployeeRequest;
import com.onehub.employee.dto.EmployeeResponse;
import com.onehub.employee.exception.DuplicateEmailException;
import com.onehub.employee.model.Employee;
import com.onehub.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee createEmployee(CreateEmployeeRequest request) {
        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException("Email already exists");
        }
        Employee employee = Employee.builder()
                .employeeNo(generateEmployeeNo())
                .title(request.getTitle())
                .firstname(request.getFirstname())
                .surname(request.getSurname())
                .dob(request.getDob())
                .gender(request.getGender())
                .email(request.getEmail())
                .address(request.getAddress())
                .build();
        return employeeRepository.save(employee);
    }

    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private EmployeeResponse toResponse(Employee employee) {
        return EmployeeResponse.builder()
                .employeeNo(employee.getEmployeeNo())
                .title(employee.getTitle())
                .firstname(employee.getFirstname())
                .surname(employee.getSurname())
                .dob(employee.getDob())
                .gender(employee.getGender())
                .email(employee.getEmail())
                .address(employee.getAddress())
                .build();
    }

    private String generateEmployeeNo() {
        return "EMP-" + UUID.randomUUID();
    }
}
