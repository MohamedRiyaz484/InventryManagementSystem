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
import jakarta.validation.constraints.NotBlank;

//---------------- CATEGORY ----------------
@Entity
@Table(name = "categories")
public class Category {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long categoryId;

 @NotBlank
 private String name;

 private String description;

 // Associations
 @ManyToOne
 @JoinColumn(name = "user_id", nullable = false)
 private User user;

 @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
 private List<Product> products;

 // Getters and Setters
}


