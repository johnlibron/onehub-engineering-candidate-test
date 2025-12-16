
package com.onehub.employee.service;

import com.onehub.employee.dto.CreateEmployeeRequest;
import com.onehub.employee.exception.DuplicateEmailException;
import com.onehub.employee.model.Employee;
import com.onehub.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    private String generateEmployeeNo() {
        return "EMP-" + UUID.randomUUID();
    }
}
