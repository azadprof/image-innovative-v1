package com.imageinnovative.employeeservices.service;

import com.imageinnovative.employeeservices.dto.EmployeTaxDTO;
import com.imageinnovative.employeeservices.dto.EmployeeDTO;

public interface EmployeeService {
    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    EmployeTaxDTO getTaxDetails(String employeeCode);
}
