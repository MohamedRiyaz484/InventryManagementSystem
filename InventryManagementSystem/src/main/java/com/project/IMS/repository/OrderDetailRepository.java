package com.project.IMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.IMS.entity.OrderDetail;

import jakarta.transaction.Transactional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{
	@Query(value = "select * from order_details where user_id=: user" , nativeQuery = true)
	List<OrderDetail> getOrderDetail(@Param("user") Integer id);
	@Modifying // To tell this is not a select query
	@Transactional
	@Query(value = "insert into order_details values (:quantity,:unit_price,default,:orderId,:pro_id)", nativeQuery = true)
	void saveOrderDetails(@Param("quantity") Integer quantity,@Param("unit_price") Double unit_price,
			    @Param("orderId") Long orderId,@Param("pro_id") Integer pro_id);
}
