package com.project.IMS.entity;

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

//---------------- CATEGORY ----------------
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "categoryId")
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
//@JsonManagedReference
 private User user;

 @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
//@JsonBackReference
 private List<Product> products;

 // Getters and Setters
}


