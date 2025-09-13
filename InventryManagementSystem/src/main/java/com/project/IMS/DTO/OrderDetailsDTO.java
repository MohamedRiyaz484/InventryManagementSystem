package com.project.IMS.DTO;


import java.time.LocalDate;

public class OrderDetailsDTO {

    private LocalDate date;       // orders.date
    private String notes;         // orders.notes
    private int quantity;         // order_details.quantity
    private double unitPrice;     // order_details.unit_price
    private int productId;        // order_details.product_id
    private String type;
    private double totalAmount;
    // Constructor
    public OrderDetailsDTO(LocalDate date, String notes, int quantity, double unitPrice, int productId,String type,double totalAmount) {
        this.date = date;
        this.notes = notes;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.productId = productId;
        this.type=type;
        this.totalAmount = totalAmount;
    }
    
    
    public double getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	// Getters and Setters
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }

	@Override
	public String toString() {
		return "OrderDetailsDTO [date=" + date + ", notes=" + notes + ", quantity=" + quantity + ", unitPrice="
				+ unitPrice + ", productId=" + productId + "]";
	}
    
}
