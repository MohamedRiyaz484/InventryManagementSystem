package com.project.IMS.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.IMS.entity.Supplier;
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long>{
	@Query(value = "select * from suppliers where user_id= :userId" , nativeQuery = true)
	List<Supplier> getByUserId(@Param("userId") Integer id);
	  Optional<Supplier> findByName(String name);
	    List<Supplier> findByUserId(Integer userId);
}
