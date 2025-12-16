
package com.onehub.employee.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(
    name = "employees",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "employeeNo"),
        @UniqueConstraint(columnNames = "email")
    }
)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeNo;
    private String title;
    private String firstname;
    private String surname;
    private LocalDate dob;
    private String gender;

    @Column(nullable = false)
    private String email;

    private String address;
}
