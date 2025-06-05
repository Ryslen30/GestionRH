package com.DSI.PortailRh.Controllers;

import com.DSI.PortailRh.entities.LeaveRequest;
import com.DSI.PortailRh.services.ILeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LeaveRequestRestController {
    @Autowired
    ILeaveRequestService ileaveRequestService;

    @GetMapping("/leave-requests")
    public List<LeaveRequest> getAllLeaveRequests() {
        return ileaveRequestService.getAllLeaveRequests();
    }
    @GetMapping("/leave-requests/{employeeId}")
    public List<LeaveRequest> getLeaveRequestsByEmployee(@PathVariable int employeeId) {
        return ileaveRequestService.getLeaveRequestByEmployee(employeeId);
    }

    @PostMapping("/leave-requests/{employeeId}")
    public boolean submitLeaveRequest(@PathVariable int employeeId, @RequestBody LeaveRequest leaveRequest) {
        return ileaveRequestService.submitLeaveRequest(employeeId, leaveRequest);
    }

    @PostMapping("/leave-requests/{employeeId}/approve/{leaveRequestId}")
    public boolean approveLeaveRequest(@PathVariable int employeeId, @PathVariable int leaveRequestId) {
        return ileaveRequestService.approveLeaveRequest(employeeId, leaveRequestId);
    }

    @PostMapping("/leave-requests/{employeeId}/reject/{leaveRequestId}")
    public boolean rejectLeaveRequest(@PathVariable int employeeId,
                                      @PathVariable int leaveRequestId,
                                      @RequestParam String reason) {
        return ileaveRequestService.rejectLeaveRequest(employeeId, leaveRequestId, reason);
    }

    @PostMapping("/leave-requests/{employeeId}/cancel/{leaveRequestId}")
    public boolean cancelLeaveRequest(@PathVariable int employeeId, @PathVariable int leaveRequestId) {
        return ileaveRequestService.cancelLeaveRequest(employeeId, leaveRequestId);
    }
}
