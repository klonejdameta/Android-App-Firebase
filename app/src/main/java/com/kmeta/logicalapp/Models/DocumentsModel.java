package com.kmeta.logicalapp.Models;

public class DocumentsModel {
    private String id;
    private String documentNumber;
    private String documentDate;
    private String amount;
    private String customer;

    public DocumentsModel(String id, String documentNumber, String documentDate, String amount, String customer) {
        this.id = id;
        this.documentNumber = documentNumber;
        this.documentDate = documentDate;
        this.amount = amount;
        this.customer = customer;
    }

    public DocumentsModel(String documentNumber, String documentDate, String amount, String customer) {
        this.documentNumber = documentNumber;
        this.documentDate = documentDate;
        this.amount = amount;
        this.customer = customer;
    }

    public DocumentsModel() {
    }

    public String getId() {
        return id;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getDocumentDate() {
        return documentDate;
    }

    public String getAmount() {
        return amount;
    }

    public String getCustomer() {
        return customer;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public void setDocumentDate(String documentDate) {
        this.documentDate = documentDate;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
