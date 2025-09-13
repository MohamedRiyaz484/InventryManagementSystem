package com.project.IMS.security;



import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import com.project.IMS.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository repo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

    	 
        
    	
    	String path = request.getServletPath();
        // Skip JWT check on login and register paths
    	if (path.startsWith("/userregister") || path.startsWith("/userlogin") ||
    		    path.startsWith("/send-otp") || path.startsWith("/verify-otp") ||
    		    path.startsWith("/forgot-password") || path.startsWith("/send-forgot-otp") ||
    		    path.startsWith("/verify-forgot-otp") || path.startsWith("/reset-password") ||
    		    path.startsWith("/view/") || path.equals("/error")) {

    		    chain.doFilter(request, response); // allow request to proceed
    		    return;
    		}

        
        HttpSession session = request.getSession(false);
        if (session != null) {
            String jwt = (String) session.getAttribute("jwtToken");
//        String jwt = (String) request.getSession().getAttribute("jwtToken");
       // String jwt = jwtUtil.generateToken(path);
        if (jwt != null) {
            try {
                String username = jwtUtil.extractUsername(jwt);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    com.project.IMS.entity.User appUser = repo.findByEmail(username).orElse(null);

                    if (appUser != null) {
                        UserDetails user = User.builder()
                                .username(username)
                                .password(appUser.getPwd())
                                .authorities(Collections.emptyList())
                                .build();

                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                        SecurityContextHolder.getContext().setAuthentication(authToken);
                        request.getSession().setAttribute("username", username);
                    }
                }
            } catch (Exception e) {
                System.out.println("[JWT Filter] Invalid token: " + e.getMessage());
                session.invalidate();
                SecurityContextHolder.clearContext();
                response.sendRedirect("/userlogin?expired=true");
                return;
            }
        } else {
            System.out.println("[JWT Filter] No JWT token found in session.");
        }

        chain.doFilter(request, response);
    }
    }}
