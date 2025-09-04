package com.project.IMS.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

//---------------- ORDER_DETAIL ----------------
@Entity
@Table(name = "order_details")
public class OrderDetail{

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long orderDetailId;

 @Positive
 private Integer quantity;

 @Positive
 private Double unitPrice;

 // Associations
 @ManyToOne
 @JoinColumn(name = "order_id", nullable = false)
 private Order order;

 @ManyToOne
 @JoinColumn(name = "product_id", nullable = false)
 private Product product;

 // Getters and Setters
}




