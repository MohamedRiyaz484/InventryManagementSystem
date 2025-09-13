package com.project.IMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.IMS.entity.User;
import com.project.IMS.repository.UserRepository;
import com.project.IMS.service.OtpService;

@Controller
public class OtpController {
	
    @Autowired
    private OtpService otpService;

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✅ Registration with OTP
    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam String name,
                          @RequestParam String email,
                          @RequestParam String phoneNumber,
                          @RequestParam String pwd,
                          Model model) {
        if (repo.findByEmail(email).isPresent()) {
            model.addAttribute("error", "Email already registered!");
            return "userRegister";
        }

        otpService.generateOtp(email);

        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("phoneNumber", phoneNumber);
        model.addAttribute("pwd", pwd);
        return "verifyOtp";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String email,
                            @RequestParam String otp,
                            @RequestParam String name,
                            @RequestParam String phoneNumber,
                            @RequestParam String pwd,
                            Model model) {
        if (otpService.validateOtp(email, otp)) {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            user.setPwd(passwordEncoder.encode(pwd));
            repo.save(user);
            model.addAttribute("msg", "Registration successful! Please login.");
            return "userLogin";
        } else {
            model.addAttribute("error", "Invalid or expired OTP. Try again.");
            return "userRegister";
        }
    }

    // ✅ Forgot Password Flow
    @GetMapping("/forgot-password")
    public String forgotPasswordPage() {
        return "forgotPassword";
    }

    @PostMapping("/send-forgot-otp")
    public String sendForgotOtp(@RequestParam String email, Model model) {
        if (!repo.findByEmail(email).isPresent()) {
            model.addAttribute("error", "Email not found!");
            return "forgotPassword";
        }
        otpService.generateOtp(email);
        model.addAttribute("email", email);
        return "verifyForgotOtp";
    }

    @PostMapping("/verify-forgot-otp")
    public String verifyForgotOtp(@RequestParam String email,
                                  @RequestParam String otp,
                                  Model model) {
        if (otpService.validateOtp(email, otp)) {
            model.addAttribute("email", email);
            return "resetPassword";
        } else {
            model.addAttribute("error", "Invalid OTP!");
            return "forgotPassword";
        }
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String email,
                                @RequestParam String pwd,
                                @RequestParam String repwd,
                                Model model) {
        if (!pwd.equals(repwd)) {
            model.addAttribute("error", "Passwords do not match!");
            return "resetPassword";
        }
        repo.findByEmail(email).ifPresent(u -> {
            u.setPwd(passwordEncoder.encode(pwd));
            repo.save(u);
        });
        model.addAttribute("msg", "Password reset successful. Please login.");
        return "userLogin";
    }
}
