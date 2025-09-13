package com.project.IMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.IMS.entity.Customer;

import jakarta.transaction.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	@Modifying // To tell this is not a select query
	@Transactional//required because insert/update needs a transaction.
	@Query(value = "insert into customers values(default,:us,:con,:name)" ,nativeQuery = true)
	void saveCustomer(@Param(value = "us") Integer us,@Param("con") String con,@Param("name") String name);
	
	@Query(value = "select * from customers where user_id = :userId" ,nativeQuery=true)
	List<Customer> getAllCustomers(@Param("userId") Integer id);
}
