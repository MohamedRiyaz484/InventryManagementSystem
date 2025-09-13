package com.project.IMS.service;

import com.project.IMS.entity.Supplier;
import com.project.IMS.entity.User;
import com.project.IMS.repository.SupplierRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found with ID: " + id));
    }

    public Supplier getSupplierByName(String name) {
        return supplierRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found with name: " + name));
    }

    public Supplier createSupplier(Supplier supplier) {
        // No user repository call; just trust the ID is valid
        return supplierRepository.save(supplier);
    }
    
    public List<Supplier> getSuppliersByUserId(Integer userId) {
        return supplierRepository.findByUserId(userId);
    }


    public Supplier updateSupplier(Long id, Supplier supplierDetails) {
        Supplier existing = getSupplierById(id);

        existing.setName(supplierDetails.getName());
        existing.setContactInfo(supplierDetails.getContactInfo());

        // ✅ Add new fields
        existing.setPhoneNumber(supplierDetails.getPhoneNumber());
        existing.setLocation(supplierDetails.getLocation());
        

        if (supplierDetails.getUser() != null) {
            User user = new User();
            user.setId(supplierDetails.getUser().getId());
            existing.setUser(user);
        }

        return supplierRepository.save(existing);
    }


    public void deleteSupplier(Long id) {
        Supplier supplier = getSupplierById(id);
        supplierRepository.delete(supplier);
    }
}
     



