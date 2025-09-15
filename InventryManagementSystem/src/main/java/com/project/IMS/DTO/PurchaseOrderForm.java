package com.project.IMS.DTO;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
//DTO for the full form
public class PurchaseOrderForm {
private Long supplierId;
private String notes;
private List<PurchaseProductDTO> products = new ArrayList<PurchaseProductDTO>();

// getters & setters
}
