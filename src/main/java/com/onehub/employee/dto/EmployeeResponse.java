package com.onehub.employee.dto;

import java.time.LocalDate;

public record EmployeeResponse(
    String employeeNo,
    String title,
    String firstname,
    String surname,
    LocalDate dob,
    String gender,
    String email,
    String address
) {}
