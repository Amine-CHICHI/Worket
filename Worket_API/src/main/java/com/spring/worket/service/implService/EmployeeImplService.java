package com.spring.worket.service.implService;

import com.spring.worket.dto.EmployeeDto;
import com.spring.worket.entity.Employee;
import com.spring.worket.exception.ElementNotFoundException;
import com.spring.worket.repository.EmployeeRepository;
import com.spring.worket.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class EmployeeImplService implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<EmployeeDto> findAllEmployees() {
        return EmployeeToEmployeeDto(employeeRepository.findAll());
    }

    @Override
    public EmployeeDto findEmployeeByID(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("Employee with id " + id + " not found"));
        return EmployeeToEmployeeDto(employee);
    }

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        employeeDto.setEmployeeCode(UUID.randomUUID().toString());
        employeeRepository.save(EmployeeDtoToEmployee(employeeDto));
        return employeeDto;
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("Employee with id " + id + " not found"));

        String name = employeeDto.getName();
        String phone = employeeDto.getPhone();
        String email = employeeDto.getEmail();
        String imageUrl = employeeDto.getImageUrl();
        String Code = employeeDto.getEmployeeCode();
        String jobTitle = employeeDto.getJobTitle();

        if (name != null && !Objects.equals(employee.getName(), name)) {
            employee.setName(name);
        }
        if (phone != null && !Objects.equals(employee.getPhone(), phone)) {
            employee.setPhone(phone);
        }
        if (email != null && !Objects.equals(employee.getEmail(), email)) {
            employee.setEmail(email);
        }
        if (imageUrl != null && !Objects.equals(employee.getImageUrl(), imageUrl)) {
            employee.setImageUrl(imageUrl);
        }
        if (Code != null && !Objects.equals(employee.getEmployeeCode(), Code)) {
            employee.setEmployeeCode(Code);
        }
        if (jobTitle != null && !Objects.equals(employee.getJobTitle(), jobTitle)) {
            employee.setJobTitle(jobTitle);
        }

        employeeRepository.save(employee);
        return EmployeeToEmployeeDto(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    private List<EmployeeDto> EmployeeToEmployeeDto(List<Employee> employees) {
        List<EmployeeDto> employeesDto = new ArrayList<>();
        employees.forEach(employee -> {
            EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

            employeeDto.setEmployeeCode(employee.getEmployeeCode());
            employeeDto.setEmail(employee.getEmail());
            employeeDto.setImageUrl(employee.getImageUrl());
            employeeDto.setJobTitle(employee.getJobTitle());
            employeeDto.setName(employee.getName());
            employeeDto.setPhone(employee.getPhone());
            employeesDto.add(employeeDto);
        });
        return employeesDto;
    }

    private Employee EmployeeDtoToEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        return employee;
    }

    private EmployeeDto EmployeeToEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }
}
