package com.expenso.expense_tracker.repository;

import com.expenso.expense_tracker.model.Transaction;
import com.expenso.expense_tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findByUserId(UUID userID);
}
