package com.expenso.expense_tracker.service;

import com.expenso.expense_tracker.model.Transaction;
import com.expenso.expense_tracker.dto.TransactionDTO;
import com.expenso.expense_tracker.dto.TransactionRequest;
import com.expenso.expense_tracker.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;
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





public Map<String, Object> getPaginatedTransactions(UUID userId, int page, int limit, String sortBy, String sortOrder) {
    Sort sort = Sort.by(Sort.Direction.fromString(sortOrder.toUpperCase()), sortBy);
    PageRequest pageable = PageRequest.of(page - 1, limit, sort);

    Page<Transaction> transactionPage = transactionRepository.findByUserId(userId, pageable);

    List<TransactionDTO> transactionDTOs = transactionPage.getContent().stream()
            .map(t -> new TransactionDTO(
                    t.getId().toString(),
                    t.getDescription(),
                    t.getAmount(),
                    t.getCategory(),
                    t.getType(),
                    t.getDate()
            ))
            .collect(Collectors.toList());

    Map<String, Object> response = new HashMap<>();
    response.put("transactions", transactionDTOs);

    Map<String, Object> pagination = new HashMap<>();
    pagination.put("page", transactionPage.getNumber() + 1);
    pagination.put("limit", transactionPage.getSize());
    pagination.put("total", transactionPage.getTotalElements());
    pagination.put("totalPages", transactionPage.getTotalPages());

    response.put("pagination", pagination);
    return response;
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
