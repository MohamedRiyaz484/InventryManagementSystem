package com.project.IMS.controller;



import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.project.IMS.Security.JwtUtil;
import com.project.IMS.entity.User;
import com.project.IMS.repository.UserRepository;
import com.project.IMS.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	private JwtUtil jwtUtil;
	

	@Autowired
	private UserRepository repo;
	@Autowired
	private UserService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/userregister")
	public String showRegisterForm(Model model) {
		model.addAttribute("user", new User());
		return "userRegister"; // changed here
	}

	@PostMapping("/doregister")
	public String processRegister(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
	   System.out.println("reached");
		if (result.hasErrors()) {
			System.out.println("error");
	        return "userRegister";
	    }
	    user.setPwd(passwordEncoder.encode(user.getPwd()));
	    repo.save(user);
	    model.addAttribute("msg", "Registration successful! Please login.");
	    return "userLogin";
	}


	@GetMapping("/userlogin")
	public String showLoginForm(Model model) {
		model.addAttribute("user", new User());
		return "userLogin"; // changed here
	}
	@PostMapping("/dologin")
	public void doLogin(
	    @RequestParam String email,
	    @RequestParam String pwd,
	    HttpServletRequest request,
	    HttpServletResponse response) throws IOException {

	    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
	    if (userDetails != null && passwordEncoder.matches(pwd, userDetails.getPassword())) {
	        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	        SecurityContextHolder.getContext().setAuthentication(auth);

	        Optional<User> user = repo.findByEmail(email);
	        if (user.isPresent()) {
	            User u = user.get();

	            HttpSession session = request.getSession(true);
	            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

	            session.setAttribute("userId", u.getId());
	            session.setAttribute("username", u.getName());
//	            
	            String token = jwtUtil.generateToken(email);  // <--- generate token
	            session.setAttribute("jwtToken", token); 

	            response.sendRedirect("/suppliers-ui");
	        } else {
	            // User not found in DB (should not happen if UserDetails returned)
	            response.sendRedirect("/userlogin?error=true");
	        }
	    } else {
	        response.sendRedirect("/userlogin?error=true");
	    }
	}

	@GetMapping("/suppliers-ui")
    public String suppliersPage() {
        // Resolves to /WEB-INF/jsp/supplier.jsp
    
        return "suppliers";
    }

	@GetMapping("/welcome")
	public String welcomePage(HttpSession session, Model model) {
		// your logic here...
		// if (username == null) { // return
		// "redirect:/login"; // } //
		// model.addAttribute("username", username);		
		String username = (String) session.getAttribute("username"); 
		return "welcome";
	}
	@PostMapping("/logout")
	public String logout(HttpServletRequest request) {
	    HttpSession session = request.getSession(false);
	    if (session != null) {
	        session.invalidate();
	    }
	    SecurityContextHolder.clearContext();
	    return "redirect:/userlogin";
	}


	
}