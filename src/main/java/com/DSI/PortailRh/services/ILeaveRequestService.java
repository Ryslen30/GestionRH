package com.DSI.PortailRh.services;

import com.DSI.PortailRh.entities.LeaveRequest;

import java.util.List;

public interface ILeaveRequestService {
    public List<LeaveRequest> getAllLeaveRequests();
    public List<LeaveRequest> getLeaveRequestByEmployee(int employeeId);
    public boolean submitLeaveRequest(int employeeId ,LeaveRequest leaveRequest);
    public boolean approveLeaveRequest(int employeeId , int leaveRequestId);
    public boolean rejectLeaveRequest(int employeeId , int leaveRequestId, String reason);
    public boolean cancelLeaveRequest(int employeeId ,int leaveRequestId);

}
