package com.danilo.payroll.controllers;

import com.danilo.payroll.domain.employee.Employee;
import com.danilo.payroll.dtos.EmployeeDTO;
import com.danilo.payroll.infra.ApiConstants;
import com.danilo.payroll.services.EmployeeNotFoundException;
import com.danilo.payroll.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(ApiConstants.EMPLOYEES_BASE_PATH)
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = this.employeeService.getAllEmployees();
        return ResponseEntity.ok().body(employees);
    }

    @PostMapping
    public ResponseEntity<Void> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee newEmployee = this.employeeService.createEmployee(employeeDTO);
        URI location = URI.create(ApiConstants.EMPLOYEES_BASE_PATH + '/' + newEmployee.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
        Employee employee = this.employeeService.getEmployeeById(id);
        return ResponseEntity.ok().body(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody EmployeeDTO employeeDTO) {
        this.employeeService.updateEmployee(id, employeeDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable long id) {
        this.employeeService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }
}
