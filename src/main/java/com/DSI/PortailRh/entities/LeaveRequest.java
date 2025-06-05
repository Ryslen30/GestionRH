package com.DSI.PortailRh.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Entity
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int LeaveRequestID;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate LeaveRequestStartDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate LeaveRequestEndDate;
    @JsonProperty("LeaveRequestReason")
    private String LeaveRequestReason;
    @Enumerated(EnumType.STRING)
    private LeaveStatus LeaveRequestStatus;
    private LocalDateTime LeaveRequestCreatedDate;
    private String rejectionReason;

    @ManyToOne
    @JoinColumn(name="idEmployee")
    private Employee employee;


    public void setLeaveRequestID(int leaveRequestID) {
        LeaveRequestID = leaveRequestID;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public void setLeaveRequestStatus(LeaveStatus leaveRequestStatus) {
        LeaveRequestStatus = leaveRequestStatus;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LeaveRequest(LocalDate leaveRequestStartDate, LocalDate leaveRequestEndDate, String leaveRequestReason) {
        LeaveRequestStartDate = leaveRequestStartDate;
        LeaveRequestEndDate = leaveRequestEndDate;
        LeaveRequestReason = leaveRequestReason;
        LeaveRequestCreatedDate = LocalDateTime.now();
        LeaveRequestStatus = LeaveStatus.Pending;
    }
    public LeaveRequest() {}
    public int getLeaveRequestID() {
        return LeaveRequestID;
    }
//
//    public void setLeaveRequestID(int leaveRequestID) {
//        LeaveRequestID = leaveRequestID;
//    }

    public LocalDate getLeaveRequestStartDate() {
        return LeaveRequestStartDate;
    }

    public void setLeaveRequestStartDate(LocalDate leaveRequestStartDate) {
        LeaveRequestStartDate = leaveRequestStartDate;
    }

    public LocalDate getLeaveRequestEndDate() {
        return LeaveRequestEndDate;
    }

    public void setLeaveRequestEndDate(LocalDate leaveRequestEndDate) {
        LeaveRequestEndDate = leaveRequestEndDate;
    }

    public String getLeaveRequestReason() {
        return LeaveRequestReason;
    }

    public void setLeaveRequestReason(String leaveRequestReason) {
        LeaveRequestReason = leaveRequestReason;
    }

    public LeaveStatus getLeaveRequestStatus() {
        return LeaveRequestStatus;
    }

    //public void setLeaveRequestStatus(LeaveStatus leaveRequestStatus) {
      //  LeaveRequestStatus = leaveRequestStatus;
   // }

    public LocalDateTime getLeaveRequestCreatedDate() {
        return LeaveRequestCreatedDate;
    }

    public void setLeaveRequestCreatedDate(LocalDateTime leaveRequestCreatedDate) {
        LeaveRequestCreatedDate = leaveRequestCreatedDate;
    }

    @Override
    public String toString() {
        return "LeaveRequest{" +
                "LeaveRequestID=" + LeaveRequestID +
                ", LeaveRequestStartDate=" + LeaveRequestStartDate +
                ", LeaveRequestEndDate=" + LeaveRequestEndDate +
                ", LeaveRequestReason='" + LeaveRequestReason + '\'' +
                ", LeaveRequestStatus=" + LeaveRequestStatus +
                ", LeaveRequestCreatedDate=" + LeaveRequestCreatedDate +
                ", rejectionReason='" + rejectionReason + '\'' +
                ", employee=" + employee +
                '}';
    }
}
