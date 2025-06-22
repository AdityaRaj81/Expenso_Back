package com.expenso.expense_tracker.controller;

import com.expenso.expense_tracker.model.Transaction;
import com.expenso.expense_tracker.dto.TransactionRequest;
import com.expenso.expense_tracker.security.JwtService;
import com.expenso.expense_tracker.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/add")
    public ResponseEntity<Transaction> addTransaction(
            @RequestBody TransactionRequest request,
            @RequestHeader("Authorization") String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid or missing Authorization header");
        }

        UUID userId = jwtService.extractUserId(authHeader);
        Transaction transaction = transactionService.addTransaction(request, userId);
        return ResponseEntity.ok(transaction);
    }
}
