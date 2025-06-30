package com.expenso.expense_tracker.service;

import com.expenso.expense_tracker.model.Transaction;
import com.expenso.expense_tracker.dto.TransactionDTO;
import com.expenso.expense_tracker.dto.TransactionRequest;
import com.expenso.expense_tracker.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    public List<TransactionDTO> getAllTransactions(UUID userId) {
    List<Transaction> transactions = transactionRepository.findByUserId(userId);

    return transactions.stream().map(t -> new TransactionDTO(
            String.valueOf(t.getId()),
            t.getDescription(),
            t.getAmount(),
            t.getCategory(),
            t.getType(),
            t.getDate()
    )).toList();
}


    private final TransactionRepository transactionRepository;

    public Transaction addTransaction(TransactionRequest request, UUID userId) {
        Transaction transaction = Transaction.builder()
                .userId(userId)
                .type(request.getType())
                .amount(request.getAmount())
                .description(request.getDescription())
                .category(request.getCategory())
                .date(request.getDate())
                .notes(request.getNotes())
                .build();
        return transactionRepository.save(transaction);
    }
}
