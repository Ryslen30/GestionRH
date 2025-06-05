package com.DSI.PortailRh.repositories;

import com.DSI.PortailRh.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
