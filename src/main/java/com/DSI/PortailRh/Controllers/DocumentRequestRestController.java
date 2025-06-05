package com.DSI.PortailRh.Controllers;

import com.DSI.PortailRh.entities.DocumentRequest;
import com.DSI.PortailRh.services.IDocumentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DocumentRequestRestController {

    @Autowired
    IDocumentRequestService idocumentRequestService;

    @GetMapping("/document-requests")
    public List<DocumentRequest> getAllDocumentRequests() {
        return idocumentRequestService.getDocumentRequests();
    }
    @GetMapping("/document-requests/{employeeId}")
    public List<DocumentRequest> getDocumentRequestsByEmployee(@PathVariable int employeeId) {
        return idocumentRequestService.getDocumentRequestByEmployee(employeeId);
    }

    @GetMapping("/document-requests/{employeeId}/status")
    public List<DocumentRequest> getDocumentRequestsByStatus(@PathVariable int employeeId,
                                                             @RequestParam String status) {
        return idocumentRequestService.getDocumentRequestByStatus(employeeId, status);
    }

    @PostMapping("/document-requests/{employeeId}/{documentId}")
    public boolean submitDocumentRequest(@PathVariable int employeeId,
                                         @PathVariable int documentId) {
        return idocumentRequestService.submitDocumentRequest(employeeId, documentId);
    }

    @PostMapping("/document-requests/{employeeId}/approve/{documentRequestId}")
    public boolean approveDocumentRequest(@PathVariable int employeeId,
                                          @PathVariable int documentRequestId) {
        return idocumentRequestService.approveDocumentRequest(employeeId, documentRequestId);
    }



    @PostMapping("/document-requests/{employeeId}/cancel/{documentRequestId}")
    public boolean cancelDocumentRequest(@PathVariable int employeeId,
                                         @PathVariable int documentRequestId) {
        return idocumentRequestService.cancelDocumentRequest(employeeId, documentRequestId);
    }
}
