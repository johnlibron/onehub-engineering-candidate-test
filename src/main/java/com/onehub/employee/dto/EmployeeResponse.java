package com.onehub.employee.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class EmployeeResponse {
    private String employeeNo;
    private String title;
    private String firstname;
    private String surname;
    private LocalDate dob;
    private String gender;
    private String email;
    private String address;
}