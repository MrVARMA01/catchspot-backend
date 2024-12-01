package com.project.catchspot.controller;
import com.project.catchspot.JWT.JwtHelper;
import com.project.catchspot.JWT.JwtRequest;
import com.project.catchspot.JWT.JwtResponse;
import com.project.catchspot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin // Update this with your frontend URL
@RestController
@RequestMapping
public class JwtAuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtHelper helper;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest request) {
        try {
            JwtResponse response = userService.login(request);
            return ResponseEntity.ok(response);  // Return the token in the response
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

}

