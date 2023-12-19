package com.imageinnovative.employeeservices.controller;

import com.imageinnovative.employeeservices.dto.EmployeTaxDTO;
import com.imageinnovative.employeeservices.dto.EmployeeDTO;
import com.imageinnovative.employeeservices.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    @NonNull
    private EmployeeService employeeServiceImpl;

    @GetMapping("/hello")
    public String helloWorld() {
        return "hello world!";
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody @Valid  EmployeeDTO employeeDTO) {

        return new ResponseEntity<>(employeeServiceImpl.saveEmployee(employeeDTO), HttpStatus.CREATED);
    }

    @GetMapping("/taxDetails/{employeeCode}")
    public ResponseEntity<EmployeTaxDTO> getTaxDetails(@PathVariable(name = "employeeCode") @NotBlank(message = "Please provide" +
            " valid employee code") String employeeCode) {
        return new ResponseEntity<>(employeeServiceImpl.getTaxDetails(employeeCode), HttpStatus.OK);
    }
}
