package com.expenso.expense_tracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
   
	@GetMapping("/")
	public String home() {
		return "Welcome to the Expense Tracker API!";
	}

}