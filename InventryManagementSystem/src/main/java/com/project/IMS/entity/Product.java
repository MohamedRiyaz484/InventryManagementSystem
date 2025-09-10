package com.project.IMS.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//---------------- PRODUCT ----------------
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "productId")
public class Product {

 @Id
 private Long productId;

 @NotBlank
 private String name;

 @NotBlank
 private String unit;

 @Positive
 private Double cost;

 @Positive
 private Double price;

 // Associations
 @ManyToOne
 @JoinColumn(name = "user_id", nullable = false)
 //@JsonManagedReference
 private User user;

 @ManyToOne
 @JoinColumn(name = "category_id", nullable = false)
 //@JsonManagedReference
 private Category category;

 @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
// @JsonBackReference
 private List<Inventory> inventories;

 @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
 //@JsonBackReference
 private List<OrderDetail> orderDetails;

 // Getters and Setters
}



