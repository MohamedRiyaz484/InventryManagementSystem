package com.project.IMS.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsDTO {
	private Long productId;
	private String name;
	//p.name,p.unit,p.cost,p.price,
	//inv.min_level,inv.max_level,inv.quantity
	private String unit;
	private Double cost;
	private Double price;
	private Integer minLevel;
	private Integer maxLevel;
	private Integer quantity;
	
}
