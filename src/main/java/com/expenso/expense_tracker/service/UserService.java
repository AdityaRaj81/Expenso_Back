package com.expenso.expense_tracker.service;

import com.expenso.expense_tracker.dto.LoginDTO;
import com.expenso.expense_tracker.model.User;
import com.expenso.expense_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // ✅ Create User
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // ✅ Login Logic
    public String loginUser(LoginDTO loginDTO) {
        Optional<User> userOpt = userRepository.findByEmail(loginDTO.getEmail());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(loginDTO.getPassword())) {
                return "Login successful!";
            }
        }

        return "Invalid email or password!";
    }
}
