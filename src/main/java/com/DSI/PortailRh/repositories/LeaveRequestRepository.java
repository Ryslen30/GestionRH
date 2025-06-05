package com.DSI.PortailRh.repositories;

import com.DSI.PortailRh.entities.DocumentRequest;
import com.DSI.PortailRh.entities.Employee;
import com.DSI.PortailRh.entities.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository  extends JpaRepository<LeaveRequest, Integer> {
    List<LeaveRequest> findByEmployee(Employee employee);
}
