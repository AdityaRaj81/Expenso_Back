// UserController.java
package com.expenso.expense_tracker.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// Import your own classes like UserService, User, LoginDTO here
import com.expenso.expense_tracker.service.UserService;
import com.expenso.expense_tracker.model.User;
import com.expenso.expense_tracker.dto.LoginDTO;

// ... your class code here
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        String response = String.valueOf(userService.loginUser(loginDTO));
        return ResponseEntity.ok(response); // Ensure loginUser() returns String

    }
}
