package com.project.IMS.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

//---------------- CUSTOMER ----------------
@Entity
@Table(name = "customers")
public class Customer {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long customerId;

 @NotBlank
 private String name;

 @Email
 private String contactInfo;

 // Associations
 @ManyToOne
 @JoinColumn(name = "user_id", nullable = false)
 private User user;

 @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
 private List<Order> orders;

 // Getters and Setters
}


