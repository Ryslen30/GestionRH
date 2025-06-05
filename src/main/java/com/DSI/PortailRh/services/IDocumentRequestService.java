package com.DSI.PortailRh.services;

import com.DSI.PortailRh.entities.Document;
import com.DSI.PortailRh.entities.DocumentRequest;

import java.util.List;

public interface IDocumentRequestService {
    public List<DocumentRequest> getDocumentRequests();
    public List<DocumentRequest> getDocumentRequestByEmployee(int employeeId);
    public List<DocumentRequest> getDocumentRequestByStatus(int employeeId,String status);
    public boolean submitDocumentRequest(int employeeId , int documentId);
    public boolean approveDocumentRequest(int employeeId ,int DocumentRequestId);
    public boolean cancelDocumentRequest(int employeeId ,int  documentRequestId);
}
