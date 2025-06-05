package com.DSI.PortailRh.entities;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
    private String password;
    private int LeaveDays;
    @Enumerated(EnumType.STRING)
    private EmployeeState state;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "employee_roles", joinColumns = @JoinColumn(name = "employee_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;



    @OneToMany(mappedBy = "employee")
    private List<LeaveRequest> LeaveRequests;

    @OneToMany(mappedBy = "docemployee")
    private List<DocumentRequest> DocumentRequests;

    public Employee() {
        this.LeaveDays = 30;
        this.state = EmployeeState.WORKING;
        this.roles = new HashSet<>();
        this.roles.add(Role.Employee);
    }
    public Employee(String name, String email) {
        this(); // Call the default constructor to set LeaveDays to 30
        this.name = name;
        this.email = email;
    }

    public EmployeeState getState() {
        return state;
    }
    public void setState(EmployeeState state) {
        this.state = state;
    }
    public int getId() {
        return id;
    }
//    public void setId(int id) {
//        this.id = id;
//    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getLeaveDays() {
        return LeaveDays;
    }

    public void setLeaveDays(int leaveDays) {
        LeaveDays = leaveDays;
    }

//    public List<LeaveRequest> getLeaveRequests() {
//        return LeaveRequests;
//    }

    public void setLeaveRequests(List<LeaveRequest> leaveRequests) {
        LeaveRequests = leaveRequests;
    }

//    public List<DocumentRequest> getDocumentRequests() {
//        return DocumentRequests;
//    }

    public void setDocumentRequests(List<DocumentRequest> documentRequests) {
        DocumentRequests = documentRequests;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", LeaveDays=" + LeaveDays +
                ", state=" + state +
                ", LeaveRequests=" + LeaveRequests +
                ", DocumentRequests=" + DocumentRequests +
                '}';
    }
}
