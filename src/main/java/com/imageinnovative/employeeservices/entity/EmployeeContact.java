package com.imageinnovative.employeeservices.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeContact{

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "id", nullable = false)
    @JdbcTypeCode (SqlTypes.BIGINT)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "employee_employee_id")
    private Employee employee;

    private String phoneNumber;

}
