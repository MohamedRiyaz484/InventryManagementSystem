package com.project.IMS.service;

import com.project.IMS.entity.Category;
import com.project.IMS.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // CREATE
    public Category createCategory(Category category) {
    	System.out.println("Enter into the service--->>");
        return categoryRepository.save(category);
    }

    // READ - get all
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // READ - get by id
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // UPDATE
    public Category updateCategory(Long id, Category updatedCategory) {
        return categoryRepository.findById(id).map(existingCategory -> {
            if (updatedCategory.getName() != null) {
                existingCategory.setName(updatedCategory.getName());
            }
            if (updatedCategory.getDescription() != null) {
                existingCategory.setDescription(updatedCategory.getDescription());
            }
            if (updatedCategory.getUser() != null) {
                existingCategory.setUser(updatedCategory.getUser());
            }
            return categoryRepository.save(existingCategory);
        }).orElse(null);
    }

    // DELETE
    public boolean deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
