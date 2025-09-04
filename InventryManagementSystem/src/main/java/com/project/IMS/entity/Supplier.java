package com.project.IMS.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//---------------- SUPPLIER ----------------
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "suppliers")
public class Supplier {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long supplierId;

 @NotBlank
 private String name;

 @Email
 private String contactInfo;

 // Associations
 @ManyToOne
 @JoinColumn(name = "user_id", nullable = false)
 @JsonManagedReference
 private User user;

 @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
 @JsonBackReference
 private List<Order> orders;

 // Getters and Setters
}

