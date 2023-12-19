package com.imageinnovative.employeeservices.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    @NotBlank(message = "Employee ID cannot be Empty")
    private String employeeId;

    @NotBlank(message = "First Name cannot be Empty")
    private String firstName;

    @NotBlank(message = "Last Name cannot be Empty")
    private String lastName;

    @NotNull(message = "Date of Joining cannot be Empty")
    @Past(message = "Date of Joining should be in the past")
    private LocalDate doj;

    @DecimalMin(value = "0.1", message = "Salary should be greater than 0.0")
    @NotNull (message = "Salary can't be blank")
    private Double salary;

    @NotEmpty(message = "At least one phone number is required")
    private List<@Pattern(regexp = "^\\d{8,18}$", message = "Phone Number should be valid")
            String> phoneNumbers = new ArrayList<>();

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be Empty")
    private String emailAddress;

}
