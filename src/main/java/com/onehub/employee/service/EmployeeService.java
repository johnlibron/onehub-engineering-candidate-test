
package com.onehub.employee.service;

import com.onehub.employee.dto.CreateEmployeeRequest;
import com.onehub.employee.dto.EmployeeResponse;
import com.onehub.employee.exception.DuplicateEmailException;
import com.onehub.employee.mapper.EmployeeMapper;
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
    private final EmployeeMapper employeeMapper;

    public EmployeeResponse createEmployee(CreateEmployeeRequest request) {
        if (employeeRepository.existsByEmail(request.email())) {
            throw new DuplicateEmailException("Email already exists");
        }
        Employee employee = employeeMapper.toEntity(request);
        employee.setEmployeeNo(generateEmployeeNo());

        Employee saved = employeeRepository.save(employee);
        return employeeMapper.toResponse(saved);
    }

    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toResponse)
                .toList();
    }

    private String generateEmployeeNo() {
        return "EMP-" + UUID.randomUUID();
    }
}
