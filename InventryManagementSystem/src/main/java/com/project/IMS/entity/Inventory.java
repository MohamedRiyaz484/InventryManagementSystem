package com.project.IMS.entity;

import java.time.OffsetDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

//---------------- INVENTORY ----------------
@Entity
@Table(name = "inventory")
public class Inventory {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long inventoryId;

 @PositiveOrZero
 private Integer quantity;

 @Positive
 private Integer minLevel;

 @Positive
 private Integer maxLevel;

 @Positive
 private Integer reorderPoint;

 private OffsetDateTime updatedAt;

 // Associations
 @ManyToOne
 @JoinColumn(name = "user_id", nullable = false)
 private User user;

 @ManyToOne
 @JoinColumn(name = "product_id", nullable = false)
 private Product product;

}




