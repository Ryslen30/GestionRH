package com.DSI.PortailRh.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int DocumentID;

    private String DocumentTitle;
    private String type;
    private LocalDate issuedDate;
    @OneToMany(mappedBy = "document")
    private List<DocumentRequest> documentRequests;

    public Document(String documentTitle, String type, LocalDate issuedDate) {

        DocumentTitle = documentTitle;
        this.type = type;
        this.issuedDate = issuedDate;
    }
    public Document() {}
    public int getDocumentID() {
        return DocumentID;
    }

    public void setDocumentID(int documentID) {
        DocumentID = documentID;
    }

    public String getDocumentTitle() {
        return DocumentTitle;
    }

    public void setDocumentTitle(String documentTitle) {
        DocumentTitle = documentTitle;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DocumentRequest> getDocumentRequests() {
        return documentRequests;
    }

    public void setDocumentRequests(List<DocumentRequest> documentRequests) {
        this.documentRequests = documentRequests;
    }

    @Override
    public String toString() {
        return "Document{" +
                "DocumentID=" + DocumentID +
                ", DocumentTitle='" + DocumentTitle + '\'' +
                ", type='" + type + '\'' +
                ", issuedDate=" + issuedDate +
                ", DocumentRequests=" + documentRequests+
                '}';
    }
}
