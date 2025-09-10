package com.project.IMS.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//---------------- SUPPLIER ----------------
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "suppliers")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "supplierId")
public class Supplier {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long supplierId;


 @NotBlank(message = "Name is required")
 @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must contain only letters and spaces")
 private String name;


 @NotBlank(message = "Contact info (email) is required")
 @Email(message = "Invalid email format")
 private String contactInfo;
 

 @NotBlank(message = "Phone number is required")
 @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
 private String phoneNumber;

 @NotBlank(message = "Location is required")
 private String location;


 @NotBlank(message = "Product type is required")
 @Pattern(regexp = "^[a-zA-Z ]+$", message = "Product type must contain only letters and spaces")
 private String productType;

 // Associations
 @ManyToOne
// @JoinColumn(name = "user_id", nullable = false)
 //@JsonManagedReference
 @JoinColumn(name = "user_id")
 @JsonIdentityReference(alwaysAsId = true)  
 private User user;

 @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
// @JsonBackReference
 @JsonIgnore
 private List<Order> orders;

 // Getters and Setters
}

