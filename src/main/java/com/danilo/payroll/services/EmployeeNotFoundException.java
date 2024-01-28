package com.danilo.payroll.services;

public class EmployeeNotFoundException extends RuntimeException {

    EmployeeNotFoundException(long id) {
        super("Could not find employee: " + id);
    }
}
