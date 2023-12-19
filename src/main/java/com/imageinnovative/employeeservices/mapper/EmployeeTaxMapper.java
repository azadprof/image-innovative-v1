package com.imageinnovative.employeeservices.mapper;

import com.imageinnovative.employeeservices.dto.EmployeTaxDTO;
import com.imageinnovative.employeeservices.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface EmployeeTaxMapper {

    EmployeeTaxMapper EMPLOYEE_TAX_MAPPER = org.mapstruct.factory.Mappers.getMapper(EmployeeTaxMapper.class);

    @Mapping (source = "employeeId", target = "employeeCode")
    @Mapping (expression = "java(employee.getSalary()*12)", target = "yearlySalary")
    EmployeTaxDTO employeeToEmployeeTaxDTO(Employee employee);
}
