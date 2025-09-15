package com.project.IMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.IMS.entity.Log;

public interface LogRepository extends JpaRepository<Log, Integer> {
	@Query(value = "select * from logs where user_id = :user", nativeQuery = true)
	List<Log> getLogs(@Param("user") Integer id);
}
