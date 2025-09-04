package com.project.IMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.IMS.entity.Category;

@Repository
// Siva's Module
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
