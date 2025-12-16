
package com.onehub.employee.controller;

import com.onehub.employee.dto.CreateEmployeeRequest;
import com.onehub.employee.model.Employee;
import com.onehub.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@Valid @RequestBody CreateEmployeeRequest request) {
        return employeeService.createEmployee(request);
    }
}
