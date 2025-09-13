package com.project.IMS.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Entity(name = "Orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "orderId")
public class Order {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long orderId;

 @NotBlank
 private String type; // "in" or "out"

 private LocalDate date = LocalDate.now();

 private String notes;
 
 private Double totalAmount;
 @ManyToOne
 @JoinColumn(name = "user_id", nullable = false)
// @JsonBackReference
 private User user;

 @ManyToOne
 @JoinColumn(name = "supplier_id")
// @JsonBackReference
 private Supplier supplier;

 @ManyToOne
 @JoinColumn(name = "customer_id")
// @JsonBackReference
 private Customer customer;

 @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
// @JsonManagedReference
 private List<OrderDetail> orderDetails;

}

