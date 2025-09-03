package com.project.IMS.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    @NotNull(message = "Quantity cannot be null")
    @PositiveOrZero(message = "Quantity must be zero or greater")
    private Integer quantity;

    @NotNull(message = "Minimum level cannot be null")
    @PositiveOrZero(message = "Minimum level must be zero or greater")
    private Integer minLevel;

    @NotNull(message = "Maximum level cannot be null")
    @Positive(message = "Maximum level must be greater than zero")
    private Integer maxLevel;

    @NotNull(message = "Reorder point cannot be null")
    @PositiveOrZero(message = "Reorder point must be zero or greater")
    private Integer reorderPoint;

    @OneToOne
    @JoinColumn(name = "product_id")
    @NotNull(message = "Product must not be null")
    private Product product;
}


