
package com.onehub.employee.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record CreateEmployeeRequest(
    @NotBlank String title,
    @NotBlank String firstname,
    @NotBlank String surname,
    @NotNull LocalDate dob,
    @NotBlank String gender,
    @Email @NotBlank String email,
    @NotBlank String address
) {}