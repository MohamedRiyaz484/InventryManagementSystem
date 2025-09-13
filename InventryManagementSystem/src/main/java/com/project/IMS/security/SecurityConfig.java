package com.project.IMS.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.IMS.service.UserService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserService service;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    // ✅ Allow GET for login, register, forgot password pages
                    .requestMatchers(HttpMethod.GET, "/userlogin", "/userregister", "/forgot-password").permitAll()
                    .requestMatchers(HttpMethod.GET, "/verifyOtp", "/verifyForgotOtp", "/resetPassword").permitAll()
                    
                    // ✅ Allow POST for login, registration, OTP, forgot/reset password
                    .requestMatchers(HttpMethod.POST, "/dologin", "/send-otp", "/verify-otp",
                                     "/send-forgot-otp", "/verify-forgot-otp", "/reset-password").permitAll()

                .requestMatchers("/view/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/error").permitAll()
                .requestMatchers(HttpMethod.POST, "/error").permitAll()


                // secure other endpoints
                .anyRequest().authenticated()
            
//            .authorizeHttpRequests(auth -> auth
//            	    .requestMatchers("/userregister", "/userlogin", "/forgot-password").permitAll()
//            	    .requestMatchers("/send-otp", "/verify-otp",
//            	                     "/send-forgot-otp", "/verify-forgot-otp", "/reset-password").permitAll()
//            	    .anyRequest().authenticated()
            	)

            .formLogin(form -> form.disable())
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/userlogin")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            );

        return http.build();
    }





    @Bean
    AuthenticationManager authMan(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authPro() {
        DaoAuthenticationProvider ap = new DaoAuthenticationProvider();
        ap.setUserDetailsService(service);
        ap.setPasswordEncoder(encoder());
        return ap;
    }
}
