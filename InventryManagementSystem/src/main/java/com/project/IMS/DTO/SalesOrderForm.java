package com.project.IMS.DTO;

import java.util.List;

public class SalesOrderForm {
    private Integer customerId;
    private String notes;
    private List<SalesProductDTO> products;

    // getters & setters
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<SalesProductDTO> getProducts() {
        return products;
    }
    public void setProducts(List<SalesProductDTO> products) {
        this.products = products;
    }
}
