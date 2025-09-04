package com.project.IMS.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//---------------- ORDER_DETAIL ----------------
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
 @JsonManagedReference
 private Product product;

 // Getters and Setters
}




