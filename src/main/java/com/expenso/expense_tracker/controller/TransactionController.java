package com.expenso.expense_tracker.controller;


import com.expenso.expense_tracker.model.Transaction;
import com.expenso.expense_tracker.dto.TransactionRequest;
import com.expenso.expense_tracker.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/add")
    public ResponseEntity<Transaction> addTransaction(@RequestBody TransactionRequest request, Principal principal) {
        // 🔐 Convert logged-in user's username to userId (via your UserService or DB query)
        UUID userId = UUID.fromString(principal.getName());
        Transaction transaction = transactionService.addTransaction(request, userId);
        return ResponseEntity.ok(transaction);
    }
}
