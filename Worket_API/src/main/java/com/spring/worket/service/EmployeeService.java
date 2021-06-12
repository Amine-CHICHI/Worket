package com.spring.worket.service;

import com.spring.worket.dto.EmployeeDto;


import java.util.List;


public interface EmployeeService {
    List<EmployeeDto> findAllEmployees();
    EmployeeDto findEmployeeByID(Long id);
    EmployeeDto addEmployee(EmployeeDto employee);
    EmployeeDto updateEmployee(Long id,EmployeeDto employee);
    void deleteEmployee(Long id);

}
