package com.DSI.PortailRh.services;

import com.DSI.PortailRh.entities.Employee;
import com.DSI.PortailRh.entities.Role;

import java.util.List;

public interface IEmployeeService {
        public boolean createEmployee(Employee employee);
        public Employee getEmployeeById(int id);
        public Employee updateEmployee(Employee employee, int id);
        public boolean deleteEmployee(int id);
        public List<Employee> getAllEmployees();
        public Employee changePassword(int id , String newPassword);
        public Employee assignRole(int id , Role role);

}
