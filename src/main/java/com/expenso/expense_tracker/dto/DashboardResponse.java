package com.expenso.expense_tracker.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardResponse {
    private double totalIncome;
    private double totalExpenses;
    private double balance;
}
