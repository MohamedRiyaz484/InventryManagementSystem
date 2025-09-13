package com.project.IMS.entity;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//---------------- INVENTORY ----------------
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "inventory")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "inventoryId")
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
// @JsonManagedReference
 private User user;

 @ManyToOne
 @JoinColumn(name = "product_id", nullable = false)
// @JsonManagedReference
 private Product product;

}




