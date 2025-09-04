package com.project.IMS.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//---------------- ORDER ----------------
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {

 @Id
 private Long orderId;

 @NotBlank
 private String type; // "in" or "out"

 private LocalDate date;

 private String notes;

 // Associations
 @ManyToOne
 @JoinColumn(name = "user_id", nullable = false)
 private User user;

 @ManyToOne
 @JoinColumn(name = "supplier_id")
 private Supplier supplier;

 @ManyToOne
 @JoinColumn(name = "customer_id")
 private Customer customer;

 @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
 private List<OrderDetail> orderDetails;

 // Getters and Setters
}

