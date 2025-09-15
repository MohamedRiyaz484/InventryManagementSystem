package com.project.IMS.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// ---------------- USERS ----------------
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {
         @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    @NotBlank(message = "Name is required")
	    private String name;

	    @Email(message = "Invalid email format")
	//    @NotBlank(message = "Email is required")
	    private String email;


	    @NotBlank(message = "Password is required")
	    	private String pwd;


	    @NotBlank(message = "Phone number is required")
	    private String phoneNumber;


    // Associations
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
   // @JsonBackReference
  @ToString.Exclude
    private List<Category> categories;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
   // @JsonBackReference
    @ToString.Exclude
    private List<Product> products;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
   // @JsonBackReference
    @ToString.Exclude
    private List<Inventory> inventories;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
   // @JsonBackReference
  // @JsonIgnore
  //  @ToString.Exclude
    private List<Supplier> suppliers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
   // @JsonBackReference
  @ToString.Exclude
    private List<Customer> customers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
   // @JsonBackReference
   @ToString.Exclude
    private List<Order> orders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
   // @JsonBackReference
    @ToString.Exclude
    private List<Log> logs;

}