package com.imageinnovative.employeeservices.repository;

import com.imageinnovative.employeeservices.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
