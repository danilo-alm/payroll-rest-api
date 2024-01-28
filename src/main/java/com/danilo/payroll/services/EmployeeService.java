package com.danilo.payroll.services;

import com.danilo.payroll.domain.employee.Employee;
import com.danilo.payroll.dtos.EmployeeDTO;
import com.danilo.payroll.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee createEmployee(EmployeeDTO employeeDTO) {
        Employee newEmployee = new Employee();

        newEmployee.setName(employeeDTO.name());
        newEmployee.setRole(employeeDTO.role());

        return this.saveEmployee(newEmployee);
    }

    public Employee updateEmployee(long id, EmployeeDTO employeeDTO) {
        Employee employee = this.employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));

        employee.setName(employeeDTO.name());
        employee.setRole(employeeDTO.role());

        return this.employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return this.employeeRepository.findAll();
    }

    public Employee getEmployeeById(long id) {
        return this.employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public void deleteEmployeeById(long id) {
        this.employeeRepository.deleteById(id);
    }


}
