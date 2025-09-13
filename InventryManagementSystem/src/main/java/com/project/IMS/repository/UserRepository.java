package com.project.IMS.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.IMS.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
}
