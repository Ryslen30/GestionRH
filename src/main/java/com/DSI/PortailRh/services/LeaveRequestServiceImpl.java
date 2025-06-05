package com.DSI.PortailRh.services;

import com.DSI.PortailRh.entities.*;
import com.DSI.PortailRh.repositories.EmployeeRepository;
import com.DSI.PortailRh.repositories.LeaveRequestRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveRequestServiceImpl implements ILeaveRequestService{
    @Autowired
    private LeaveRequestRepository leaveRequestRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }

    @Override
    public List<LeaveRequest> getLeaveRequestByEmployee(int employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        return leaveRequestRepository.findByEmployee(employee);
    }

    @Override
    public boolean submitLeaveRequest(int employeeId, LeaveRequest leaveRequest) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + employeeId));

        LocalDate startDate = leaveRequest.getLeaveRequestStartDate();
        LocalDate endDate = leaveRequest.getLeaveRequestEndDate();


        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start date and end date cannot be null");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before the start date");
        }
        // Fetch all leave requests for the given employee
        List<LeaveRequest> existingRequests = leaveRequestRepository.findByEmployee(employee);

        for (LeaveRequest existingRequest : existingRequests) {
            // Check if the leave request overlaps
            if ((startDate.isBefore(existingRequest.getLeaveRequestEndDate()) && endDate.isAfter(existingRequest.getLeaveRequestStartDate())) ||
                    (startDate.isEqual(existingRequest.getLeaveRequestStartDate()) && endDate.isEqual(existingRequest.getLeaveRequestEndDate()))) {
                throw new IllegalArgumentException("Leave request overlaps with an existing request");
            }
        }
        // Calculate days between start and end date
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);


        if (employee.getLeaveDays() - daysBetween >= 0 && employee.getState() == EmployeeState.WORKING ) {
            leaveRequest.setLeaveRequestStatus(LeaveStatus.Pending);
            leaveRequest.setLeaveRequestCreatedDate(LocalDateTime.now());
            leaveRequest.setEmployee(employee);
            leaveRequestRepository.save(leaveRequest);

            return true;
        }

        return false;
    }


    @Override
    public boolean approveLeaveRequest(int employeeId, int leaveRequestId) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() -> new EntityNotFoundException("Document request not found with ID: " + leaveRequestId));
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + employeeId));

        LocalDate startDate = leaveRequest.getLeaveRequestStartDate();
        LocalDate endDate = leaveRequest.getLeaveRequestEndDate();

        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        leaveRequest.setLeaveRequestStatus(LeaveStatus.Approved);
        employee.setLeaveDays(employee.getLeaveDays() - (int) daysBetween);
        employeeRepository.save(employee);
        leaveRequestRepository.save(leaveRequest);
        return true;
    }

    @Override
    public boolean rejectLeaveRequest(int employeeId, int leaveRequestId, String reason) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() -> new EntityNotFoundException("Document request not found with ID: " + leaveRequestId));

        leaveRequest.setRejectionReason(reason);
        leaveRequest.setLeaveRequestStatus(LeaveStatus.Rejected);
        leaveRequestRepository.save(leaveRequest);
        return true;
    }

    @Override
    public boolean cancelLeaveRequest(int employeeId, int leaveRequestId) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() -> new EntityNotFoundException("Document request not found with ID: " + leaveRequestId));

        if (leaveRequest.getEmployee().getId() != employeeId) {
            throw new SecurityException("You can only cancel your own document requests.");
        }

        leaveRequest.setLeaveRequestStatus(LeaveStatus.Canceled);
        leaveRequestRepository.save(leaveRequest);
        return true;
    }
}
