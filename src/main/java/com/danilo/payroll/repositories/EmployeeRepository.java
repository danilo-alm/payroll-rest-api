package com.danilo.payroll.repositories;

import com.danilo.payroll.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
