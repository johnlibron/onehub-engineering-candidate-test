
package com.onehub.employee.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class CreateEmployeeRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String firstname;

    @NotBlank
    private String surname;

    @NotNull
    private LocalDate dob;

    @NotBlank
    private String gender;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String address;
}
