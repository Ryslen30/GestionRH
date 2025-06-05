package com.DSI.PortailRh.Controllers;

import com.DSI.PortailRh.entities.Employee;
import com.DSI.PortailRh.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeRestController {
    @Autowired
    IEmployeeService iemployeeService;

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return iemployeeService.getAllEmployees();
    }
    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        return iemployeeService.getEmployeeById(id);
    }
    @PostMapping("/employees")
    public boolean addEmployee(@RequestBody Employee employee) {
        return iemployeeService.createEmployee(employee);
    }
    @DeleteMapping("/employees/{id}")
    public boolean deleteEmployee(@PathVariable int id) {
        return iemployeeService.deleteEmployee(id);
    }
    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        return iemployeeService.updateEmployee(employee, id);
    }






}
