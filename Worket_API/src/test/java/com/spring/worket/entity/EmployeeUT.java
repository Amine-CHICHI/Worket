package com.spring.worket.entity;

import com.spring.worket.dto.EmployeeDto;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.assertEquals;

public class EmployeeUT {

    private static final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void checkEmployeeMapping() {
//      Employee ----> DTO
        Employee employeeCreation = new Employee();
        employeeCreation.setEmployeeCode("123456");
        employeeCreation.setEmail("aminechichi@gmail.com");
        employeeCreation.setImageUrl("src/images/test.png");
        employeeCreation.setName("amine chichi");
        employeeCreation.setJobTitle("Software Engineer");
        employeeCreation.setPhone("0622239803");

        EmployeeDto employeeDto = modelMapper.map(employeeCreation, EmployeeDto.class);

        assertEquals(employeeCreation.getEmployeeCode(), employeeDto.getEmployeeCode());
        assertEquals(employeeCreation.getEmail(), employeeDto.getEmail());
        assertEquals(employeeCreation.getImageUrl(), employeeDto.getImageUrl());
        assertEquals(employeeCreation.getName(), employeeDto.getName());
        assertEquals(employeeCreation.getJobTitle(), employeeDto.getJobTitle());
        assertEquals(employeeCreation.getPhone(), employeeDto.getPhone());

//       DTO  ----> Employee
        EmployeeDto employeeDto1 = new EmployeeDto();
        employeeDto1.setEmployeeCode("123456");
        employeeDto1.setEmail("aminechichi@gmail.com");
        employeeDto1.setImageUrl("src/images/test.png");
        employeeDto1.setName("amine chichi");
        employeeDto1.setJobTitle("Software Engineer");
        employeeDto1.setPhone("0622239803");

        Employee employee = modelMapper.map(employeeDto1, Employee.class);

        assertEquals(employee.getEmployeeCode(), employeeDto1.getEmployeeCode());
        assertEquals(employee.getEmail(), employeeDto1.getEmail());
        assertEquals(employee.getImageUrl(), employeeDto1.getImageUrl());
        assertEquals(employee.getName(), employeeDto1.getName());
        assertEquals(employee.getJobTitle(), employeeDto1.getJobTitle());
        assertEquals(employee.getPhone(), employeeDto1.getPhone());
    }
}
