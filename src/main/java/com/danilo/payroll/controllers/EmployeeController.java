package com.danilo.payroll.controllers;

import com.danilo.payroll.domain.employee.Employee;
import com.danilo.payroll.domain.employee.EmployeeModelAssembler;
import com.danilo.payroll.dtos.EmployeeDTO;
import com.danilo.payroll.infra.ApiConstants;
import com.danilo.payroll.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(ApiConstants.EMPLOYEES_BASE_PATH)
public class EmployeeController {
    private final EmployeeService service;
    private final EmployeeModelAssembler assembler;

    @Autowired
    public EmployeeController(EmployeeService service, EmployeeModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Employee>>> getAllEmployees() {
        List<EntityModel<Employee>> employees = this.service.getAllEmployees().stream()
          .map(assembler::toModel)
          .collect(Collectors.toList());

        return ResponseEntity.ok().body(
          CollectionModel.of(employees,
            linkTo(methodOn(EmployeeController.class).getAllEmployees()).withSelfRel())
        );
    }

    @PostMapping
    public ResponseEntity<Void> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee newEmployee = this.service.createEmployee(employeeDTO);
        URI location = URI.create(ApiConstants.EMPLOYEES_BASE_PATH + '/' + newEmployee.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Employee>> getEmployeeById(@PathVariable long id) {
        Employee employee = this.service.getEmployeeById(id);
        return ResponseEntity.ok().body(assembler.toModel(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody EmployeeDTO employeeDTO) {
        this.service.updateEmployee(id, employeeDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable long id) {
        this.service.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }
}
