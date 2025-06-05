package com.DSI.PortailRh.repositories;

import com.DSI.PortailRh.entities.DocumentRequest;
import com.DSI.PortailRh.entities.DocumentRequestStatus;
import com.DSI.PortailRh.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRequestRepository extends JpaRepository<DocumentRequest, Integer> {
    List<DocumentRequest> findByDocemployee(Employee docemployee);
    List<DocumentRequest> findByDocemployeeAndDocumentRequestStatus(Employee docemployee, DocumentRequestStatus documentStatus);


}

