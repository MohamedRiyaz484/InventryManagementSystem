package com.project.IMS.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// ---------------- USERS ----------------
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    // Associations
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  //  @JsonBackReference
    private List<Category> categories;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
   // @JsonBackReference
    private List<Product> products;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
   // @JsonBackReference
    private List<Inventory> inventories;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  //  @JsonBackReference
    private List<Supplier> suppliers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  //  @JsonBackReference
    private List<Customer> customers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  //  @JsonBackReference
    private List<Order> orders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  //  @JsonBackReference
    private List<Log> logs;

}


