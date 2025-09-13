package com.project.IMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.IMS.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	@Query(value = "select * from products where user_id= :userId" , nativeQuery = true)
	List<Product> getByUserId(@Param("userId") Integer id);
	
	Product findByProductId(Integer id);
	
	@Query(value = "SELECT p.product_id,p.name,p.unit,p.cost,p.price,inv.min_level,inv.max_level,inv.quantity " +
            "FROM products p " +
            "JOIN inventory inv ON p.product_id = inv.product_id " +
            "WHERE p.user_id = :user",
    nativeQuery = true)
	List<Object[]> getProducts(@Param("user") Integer id);
}
