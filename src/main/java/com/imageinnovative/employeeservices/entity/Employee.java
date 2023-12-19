package com.imageinnovative.employeeservices.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "employee")
public class Employee{

    @Id
    private String employeeId;

    private String firstName;

    private String lastName;

    private LocalDate doj;

    private Double salary;

    @OneToMany (mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<EmployeeContact> phoneNumbers = new ArrayList<>();

    private String emailAddress;
}
