package com.project.IMS.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//DTO for each product row
public class PurchaseProductDTO {
 private Integer productId;
 private Integer quantity;
 private Double unitPrice;

 // getters & setters
}



