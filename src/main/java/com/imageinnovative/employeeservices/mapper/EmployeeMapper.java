package com.imageinnovative.employeeservices.mapper;

import com.imageinnovative.employeeservices.dto.EmployeeDTO;
import com.imageinnovative.employeeservices.entity.Employee;
import com.imageinnovative.employeeservices.entity.EmployeeContact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper EMPLOYEE_MAPPER = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDTO employeeToEmployeeDTO(Employee employee);

    @Mapping(target = "phoneNumbers", expression = "java(mapContact(employeeDTO.getPhoneNumbers(), employee))")
    Employee employeeDTOToEmployee(EmployeeDTO employeeDTO);

    default List<String> map(List<EmployeeContact> contacts){
        List<String> phoneNumbers = new ArrayList<>();
        if(contacts != null && !contacts.isEmpty()) {
            contacts.forEach(phone -> phoneNumbers.add(phone.getPhoneNumber()));
        }
        return phoneNumbers;
    }

    default List<EmployeeContact> mapContact(List<String> contacts, Employee employee){
        List<EmployeeContact> phoneNumbers = new ArrayList<>();
        AtomicLong i = new AtomicLong(1);
        if(contacts != null && !contacts.isEmpty()) {
            contacts.forEach(phone -> phoneNumbers.add(new EmployeeContact(i.getAndIncrement(), employee, phone)));
        }
        return phoneNumbers;
    }
}
