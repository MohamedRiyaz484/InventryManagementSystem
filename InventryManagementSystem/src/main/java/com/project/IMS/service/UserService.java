package com.project.IMS.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.IMS.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		com.project.IMS.entity.User user = repo.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return new User(user.getEmail(), user.getPwd(), Collections.emptyList());
	}
}
