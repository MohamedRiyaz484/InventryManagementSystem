package com.project.IMS.entity;

import java.util.List;

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
 private User user;

 @ManyToOne
 @JoinColumn(name = "category_id", nullable = false)
 private Category category;

 @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
 private List<Inventory> inventories;

 @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
 private List<OrderDetail> orderDetails;

 // Getters and Setters
}



