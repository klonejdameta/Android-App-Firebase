package com.kmeta.logicalapp.Models;

public class DocumentsModel {
    private Integer id;
    private String documentNumber;
    private String documentDate;
    private String amount;
    private String customer;

    public DocumentsModel(Integer id, String documentNumber, String documentDate, String amount, String customer) {
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

    public Integer getId() {
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
}
