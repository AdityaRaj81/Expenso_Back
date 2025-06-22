package com.expenso.expense_tracker.controller;

import com.expenso.expense_tracker.dto.DashboardResponse;
import com.expenso.expense_tracker.security.JwtService;
import com.expenso.expense_tracker.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<DashboardResponse> getDashboardData(@RequestHeader("Authorization") String token) {
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Unauthorized: Missing token");
        }

        UUID userId = jwtService.extractUserId(token);
        DashboardResponse response = dashboardService.getDashboardData(userId);
        return ResponseEntity.ok(response);
    }
}
