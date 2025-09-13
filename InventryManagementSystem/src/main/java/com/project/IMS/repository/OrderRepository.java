package com.project.IMS.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.IMS.entity.Order;

import jakarta.transaction.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	 @Query(value = "SELECT o.date, o.notes, od.quantity, od.unit_price, od.product_id ,o.type,o.total_amount " +
             "FROM orders o " +
             "JOIN order_details od ON o.order_id = od.order_id " +
             "WHERE o.user_id = :user",
     nativeQuery = true)
	List<Object[]> getOrders(@Param("user") Integer id);
	@Modifying // To tell this is not a select query
	@Transactional
	@Query(value = "insert into orders values (:date,null,default,:supId,:userId,:notes,:type)",nativeQuery = true)
	Order addPurchase(@Param("date") Date date,@Param("supId") Integer supId,@Param("userId")Integer userId,@Param("notes") String notes,@Param("type")String type);
	@Modifying // To tell this is not a select query
	@Transactional
	@Query(value = "insert into orders values (:date,:cusId,default,null,:userId,:notes,:type)",nativeQuery = true)
	Order addSales(@Param("date") Date date,@Param("cusId") Integer cusId,@Param("userId")Integer userId,@Param("notes") String notes,@Param("type")String type);
	
}
