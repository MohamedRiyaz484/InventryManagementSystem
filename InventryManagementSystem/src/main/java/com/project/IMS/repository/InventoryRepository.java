package com.project.IMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.IMS.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> 
{
	@Query(value = "select * from inventory where product_id = :proID", nativeQuery = true)
	Inventory getByProductId(@Param("proID") Integer pro_id);
	
	@Query(value = "select * from inventory where user_id= :userId" , nativeQuery = true)
	List<Inventory> getByUserId(@Param("userId") Integer id);
	
}
