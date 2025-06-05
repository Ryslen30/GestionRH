package com.DSI.PortailRh.services;

import com.DSI.PortailRh.entities.*;
import com.DSI.PortailRh.repositories.DocumentRepository;
import com.DSI.PortailRh.repositories.DocumentRequestRepository;
import com.DSI.PortailRh.repositories.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DocumentRequestServiceImpl implements IDocumentRequestService{
    @Autowired
    private DocumentRequestRepository documentRequestRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DocumentRepository documentRepository;


    @Override
    public List<DocumentRequest> getDocumentRequests() {
        return documentRequestRepository.findAll();
    }

    @Override
    public List<DocumentRequest> getDocumentRequestByEmployee(int employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        return documentRequestRepository.findByDocemployee(employee);
    }

    @Override
    public List<DocumentRequest> getDocumentRequestByStatus(int employeeId ,String status) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + employeeId));

        DocumentRequestStatus documentStatus;

        try {
            documentStatus = DocumentRequestStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }

        return documentRequestRepository.findByDocemployeeAndDocumentRequestStatus(employee, documentStatus);
    }

    @Override
    public boolean submitDocumentRequest(int employeeId,  int documentId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + employeeId));
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new EntityNotFoundException("Document not found with ID: " + documentId));
        DocumentRequest documentRequest = new DocumentRequest();
        documentRequest.setDocemployee(employee);
        documentRequest.setDocument(document);
        documentRequest.setDocumentRequestCreatedDate(LocalDateTime.now());
        documentRequest.setDocumentRequestStatus(DocumentRequestStatus.Pending);
        documentRequestRepository.save(documentRequest);
        return true;

    }

    @Override
    public boolean approveDocumentRequest(int employeeId, int documentRequestId) {
        DocumentRequest documentRequest = documentRequestRepository.findById(documentRequestId)
                .orElseThrow(() -> new EntityNotFoundException("Document request not found with ID: " + documentRequestId));


        documentRequest.setDocumentRequestStatus(DocumentRequestStatus.Treated);
        documentRequestRepository.save(documentRequest);
        return true;
    }



    @Override
    public boolean cancelDocumentRequest(int employeeId, int documentRequestId) {
        DocumentRequest documentRequest = documentRequestRepository.findById(documentRequestId)
                .orElseThrow(() -> new EntityNotFoundException("Document request not found with ID: " + documentRequestId));

        if (documentRequest.getDocemployee().getId() != employeeId) {
            throw new SecurityException("You can only cancel your own document requests.");
        }

        documentRequest.setDocumentRequestStatus(DocumentRequestStatus.Canceled);
        documentRequestRepository.save(documentRequest);
        return true;
    }
}
