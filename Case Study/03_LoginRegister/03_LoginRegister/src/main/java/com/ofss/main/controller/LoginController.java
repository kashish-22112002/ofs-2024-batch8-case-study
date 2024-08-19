package com.ofss.main.controller;

import com.ofss.main.domain.LoginDetail;
import com.ofss.main.service.LoginDetailService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginDetailService loginDetailService;

    // Endpoint for user login
    @PostMapping("/authenticate")
    @CrossOrigin
    public ResponseEntity<Map<String, Boolean>> authenticate(@RequestBody LoginDetail loginDetail) {
    	String username = loginDetail.getUsername();
    	String password = loginDetail.getPassword();
        boolean isAuthenticated = loginDetailService.authenticate(username, password);
        if (isAuthenticated) {
            return ResponseEntity.ok(Map.of("authenticated", true));
        } else {
            return ResponseEntity.status(401).body(Map.of("authenticated", false));
        }
    
    }
   
    // Endpoint to fetch login details by username
    @GetMapping("/{username}")
    @CrossOrigin
    public ResponseEntity<LoginDetail> getLoginDetailByUsername(@PathVariable String username) {
        LoginDetail loginDetail = loginDetailService.getLoginDetailByUsername(username);
        if (loginDetail != null) {
            return ResponseEntity.ok(loginDetail);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
