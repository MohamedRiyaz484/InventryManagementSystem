package com.project.IMS.controller;

import com.project.IMS.entity.Supplier;
import com.project.IMS.service.SupplierService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
@CrossOrigin( "*")
public class SupplierController {

    private final SupplierService supplierService;

    // GET /api/suppliers/getAll
   // @GetMapping("/getAll")
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Supplier>> getSuppliers(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Supplier> suppliers = supplierService.getSuppliersByUserId(userId);
        return ResponseEntity.ok(suppliers);
    }


    // GET /api/suppliers/getById/{id}
    @GetMapping("/getById/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    // GET /api/suppliers/getByName/{name}
    @GetMapping("/getByName/{name}")
    public ResponseEntity<Supplier> getSupplierByName(@PathVariable String name) {
        return ResponseEntity.ok(supplierService.getSupplierByName(name));
    }

    // POST /api/suppliers/create
    @PostMapping("/create")
   // @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Supplier> createSupplier(@Valid @RequestBody Supplier supplier) {
        Supplier createdSupplier = supplierService.createSupplier(supplier);
        return new ResponseEntity<>(createdSupplier, HttpStatus.CREATED);
    }

    // PUT /api/suppliers/update/{id}
    @PutMapping("/update/{id}")
    public ResponseEntity<Supplier> updateSupplier(@Valid @PathVariable Long id, @RequestBody Supplier supplier) {
        return ResponseEntity.ok(supplierService.updateSupplier(id, supplier));
    }

    // DELETE /api/suppliers/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }
}