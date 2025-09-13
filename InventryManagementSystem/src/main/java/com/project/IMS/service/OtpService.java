package com.project.IMS.service;

import java.time.LocalDateTime;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.project.IMS.entity.OtpVerification;
import com.project.IMS.repository.OtpRepository;

import jakarta.transaction.Transactional;

@Service
public class OtpService {
    @Autowired private JavaMailSender mailSender;
    @Autowired private OtpRepository otpRepo;

    @Transactional // <-- Add this annotation here
    public String generateOtp(String email) {
        String otp = String.valueOf(100000 + new Random().nextInt(900000));
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(10);

        otpRepo.deleteByEmail(email); // clear old OTPs
        otpRepo.save(new OtpVerification(null, email, otp, expiry, 2));

        sendOtpEmail(email, otp);
        return otp;
    }

    private void sendOtpEmail(String to, String otp) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject("Your OTP Code");
        msg.setText("Your OTP is: " + otp + " (valid for 10 minutes)");
        mailSender.send(msg);
    }

    public boolean validateOtp(String email, String enteredOtp) {
        return otpRepo.findByEmail(email).map(record -> {
            if (record.getAttemptsLeft() <= 0) return false;
            if (LocalDateTime.now().isAfter(record.getExpiryTime())) return false;

            if (record.getOtp().equals(enteredOtp)) {
                otpRepo.delete(record);
                return true;
            } else {
                record.setAttemptsLeft(record.getAttemptsLeft() - 1);
                otpRepo.save(record);
                return false;
            }
        }).orElse(false);
    }
}
