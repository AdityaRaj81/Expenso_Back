package com.expenso.expense_tracker.repository;

import com.expenso.expense_tracker.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID>, JpaSpecificationExecutor<Transaction> {
    List<Transaction> findByUserId(UUID userID);

    Page<Transaction> findByUserId(UUID userId, Pageable pageable);

    // ✅ Add this below line
    Optional<Transaction> findByIdAndUserId(UUID id, UUID userId);
}
