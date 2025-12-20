package com.vaishnavi.backend.controller;

import com.vaishnavi.backend.entity.User;
import com.vaishnavi.backend.repository.UserRepository;
import com.vaishnavi.backend.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // SIGNUP
    @PostMapping("/signup")
    public String signup(@RequestBody Map<String, String> request) {

        String email = request.get("email");
        String password = request.get("password");

        if (userRepository.findByEmail(email).isPresent()) {
            return "User already exists";
        }

        User user = new User(email, passwordEncoder.encode(password));
        userRepository.save(user);

        return "User registered successfully";
    }

    // LOGIN
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> request) {

        String email = request.get("email");
        String password = request.get("password");

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return JwtUtil.generateToken(email);
    }
}
