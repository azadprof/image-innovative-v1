package com.imageinnovative.employeeservices.service.impl;

import com.imageinnovative.employeeservices.dto.EmployeTaxDTO;
import com.imageinnovative.employeeservices.dto.EmployeeDTO;
import com.imageinnovative.employeeservices.entity.Employee;
import com.imageinnovative.employeeservices.mapper.EmployeeMapper;
import com.imageinnovative.employeeservices.mapper.EmployeeTaxMapper;
import com.imageinnovative.employeeservices.repository.EmployeeRepository;
import com.imageinnovative.employeeservices.service.EmployeeService;
import com.imageinnovative.employeeservices.exception.EmployeeNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @NonNull
    private EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper = EmployeeMapper.EMPLOYEE_MAPPER;

    private final EmployeeTaxMapper employeeTaxMapper = EmployeeTaxMapper.EMPLOYEE_TAX_MAPPER;

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        return employeeMapper.employeeToEmployeeDTO(employeeRepository.save(employeeMapper.employeeDTOToEmployee(employeeDTO)));
    }

    @Override
    public EmployeTaxDTO getTaxDetails(String employeeCode) {
        Employee employee =
                employeeRepository.findById(employeeCode).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with employee code: " + employeeCode));

        EmployeTaxDTO employeTaxDTO = employeeTaxMapper.employeeToEmployeeTaxDTO(employee);
        double totalSalary = getTotalSalary(employee.getSalary(), employee.getDoj());
        employeTaxDTO.setTaxAmount(calculateTax(totalSalary));
        employeTaxDTO.setCessAmount(calculateCess(totalSalary));

        return employeTaxDTO;
    }

    private double getTotalSalary(Double salary, LocalDate doj) {
        LocalDate startOfFinancialYear = LocalDate.of(LocalDate.now().getYear(), 4, 1);
        if(doj.isBefore(startOfFinancialYear)) {
            return salary * 12;
        } else {
            int monthsNotWorked = startOfFinancialYear.until(doj).getMonths();
            int daysNotWorked = doj.getDayOfMonth() - 1;
            return (salary * (12-monthsNotWorked)) - ( (salary/30) * daysNotWorked);
        }
    }

    private double calculateTax(double totalSalary) {
        if(totalSalary <= 250000) {
            return 0;
        } else if(totalSalary <= 500000) {
            return ((totalSalary - 250000) * (0.05));
        } else if(totalSalary <= 1000000) {
            return 12500 + ((totalSalary - 500000) * (0.10));
        } else {
            return 62500+ ((totalSalary - 1000000) * (0.20));
        }
    }
    private double calculateCess(double totalSalary) {
        return totalSalary > 2500000 ? (totalSalary - 2500000) * (0.02) : 0;
    }
}
