package com.DSI.PortailRh.services;

import com.DSI.PortailRh.entities.Employee;
import com.DSI.PortailRh.entities.EmployeeState;
import com.DSI.PortailRh.entities.Role;
import com.DSI.PortailRh.repositories.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements IEmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public boolean createEmployee(Employee employee) {
        if (employee == null) return false;
        if (employee.getRoles() == null || employee.getRoles().isEmpty()) {
            employee.setRoles(new HashSet<>(Set.of(Role.Employee)));
        }
        employee.getRoles().add(Role.Employee);
        employeeRepository.save(employee);
        return true;
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + id));
    }

    @Override
    public Employee updateEmployee(Employee employee, int id) { // without password update
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + id));

        if (employee.getName() != null) {
            existingEmployee.setName(employee.getName());
        }
        if (employee.getEmail() != null) {
            existingEmployee.setEmail(employee.getEmail());
        }


        return employeeRepository.save(existingEmployee);
    }

    @Override
    public boolean deleteEmployee(int id) {
        if (!employeeRepository.existsById(id)) return false;
        employeeRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee changePassword(int id, String newPassword) { // cryptage spring security
        return null;
    }

    @Override
//    @PreAuthorize("hasRole('ADMIN')")
    public Employee assignRole(int employeeId, Role role) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.getRoles().add(role);
            return employeeRepository.save(employee);
        }

        return null;
    }
}
