package com.project.IMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.IMS.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
