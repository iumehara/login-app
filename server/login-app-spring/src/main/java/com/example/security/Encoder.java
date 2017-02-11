package com.example.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

public class Encoder {
    private BCryptPasswordEncoder bcrypt;

    public Encoder() {
        SecureRandom secureRandom = new SecureRandom();
        bcrypt = new BCryptPasswordEncoder(10, secureRandom);
    }

    public String encode(String rawPassword) {
        return bcrypt.encode(rawPassword);
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return bcrypt.matches(rawPassword, encodedPassword);
    }
}
