package com.Major.Project.Controller;

import com.Major.Project.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestParam String username) {
        // Yahan tum apna password validation logic likh sakte ho
        return jwtUtil.generateToken(username);
    }
}