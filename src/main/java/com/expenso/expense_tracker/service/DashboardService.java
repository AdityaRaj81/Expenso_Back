package com.expenso.expense_tracker.service;


import com.expenso.expense_tracker.dto.DashboardResponse;
import com.expenso.expense_tracker.dto.TransactionDTO;
import com.expenso.expense_tracker.model.Transaction;
import com.expenso.expense_tracker.model.User;
import com.expenso.expense_tracker.repository.UserRepository;

import com.expenso.expense_tracker.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public DashboardResponse getDashboardData(UUID userId) {
        // ✅ Step 1: Fetch user from DB using userId
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        List<Transaction> transactions = transactionRepository.findByUserId(userId);

        double totalIncome = transactions.stream()
                .filter(t -> t.getType().equalsIgnoreCase("income"))
                .mapToDouble(Transaction::getAmount)
                .sum();

        double totalExpenses = transactions.stream()
                .filter(t -> t.getType().equalsIgnoreCase("expense"))
                .mapToDouble(Transaction::getAmount)
                .sum();

        double balance = totalIncome - totalExpenses;

        List<TransactionDTO> recentTransactions = transactions.stream()
                .sorted((t1, t2) -> t2.getDate().compareTo(t1.getDate()))
                .limit(5)
                .map(t -> new TransactionDTO(
                        t.getId().toString(),
                        t.getDescription(),
                        t.getAmount(),
                        t.getCategory(),
                        t.getType(),
                        t.getDate()
                ))
                .collect(Collectors.toList());

        return new DashboardResponse(totalIncome, totalExpenses, balance, recentTransactions);
    }
}
