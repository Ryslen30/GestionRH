package com.DSI.PortailRh.entities;

import jakarta.persistence.*;
import org.w3c.dom.DocumentType;


import java.time.LocalDateTime;


@Entity
public class DocumentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int documentRequestID;
    @Enumerated(EnumType.STRING)
    private DocumentRequestStatus documentRequestStatus;
    private LocalDateTime DocumentRequestCreatedDate;



    @ManyToOne
    @JoinColumn(name="idEmployee" ,nullable = false)
    private Employee docemployee;

    @ManyToOne
    @JoinColumn(name = "document_id",nullable = false)
    private Document document;

    public DocumentRequest(LocalDateTime documentRequestCreatedDate) {
        this.documentRequestStatus = DocumentRequestStatus.Pending;
        DocumentRequestCreatedDate = LocalDateTime.now();
    }
    public DocumentRequest() {}
    public int getDocumentID() {
        return documentRequestID;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Employee getDocemployee() {
        return docemployee;
    }

    public void setDocemployee(Employee docemployee) {
        this.docemployee = docemployee;
    }



//    public void setDocumentID(int documentID) {
//        this.documentRequestID = documentID;
//    }


    public int getDocumentRequestID() {
        return documentRequestID;
    }

    public void setDocumentRequestID(int documentRequestID) {
        this.documentRequestID = documentRequestID;
    }


    public LocalDateTime getDocumentRequestCreatedDate() {
        return DocumentRequestCreatedDate;
    }

    public void setDocumentRequestCreatedDate(LocalDateTime documentRequestCreatedDate) {
        DocumentRequestCreatedDate = documentRequestCreatedDate;
    }



    public DocumentRequestStatus getDocumentRequestStatus() {
        return documentRequestStatus;
    }

    public void setDocumentRequestStatus(DocumentRequestStatus documentRequestStatus) {
        this.documentRequestStatus = documentRequestStatus;
    }

    @Override
    public String toString() {
        return "DocumentRequest{" +
                "documentRequestID=" + documentRequestID +
                ", documentRequestStatus=" + documentRequestStatus +
                ", DocumentRequestCreatedDate=" + DocumentRequestCreatedDate +
                ", docemployee=" + docemployee +
                ", document=" + document +
                '}';
    }
}

