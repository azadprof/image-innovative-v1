package com.imageinnovative.employeeservices.repository;

import com.imageinnovative.employeeservices.entity.EmployeeContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeContactRepository extends JpaRepository<EmployeeContact, Long> {
}
