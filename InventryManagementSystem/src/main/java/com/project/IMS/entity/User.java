package com.project.IMS.entity;


import java.util.List;

import jakarta.validation.constraints.Email;




import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
//	    @Pattern(
//	    	    regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
//	    	    message = "Password must be at least 8 characters and include letters, numbers, and special characters"
//	    	)
	    	private String pwd;


	    @NotBlank(message = "Phone number is required")
	   // @Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits")
	    private String phoneNumber;


    // Associations
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
   // @JsonBackReference
    private List<Category> categories;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
   // @JsonBackReference
    private List<Product> products;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
   // @JsonBackReference
    private List<Inventory> inventories;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
   // @JsonBackReference
   // @JsonIgnore
    private List<Supplier> suppliers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
   // @JsonBackReference
    private List<Customer> customers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
   // @JsonBackReference
    private List<Order> orders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
   // @JsonBackReference
    private List<Log> logs;

}


