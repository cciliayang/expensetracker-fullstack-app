package com.cciliacode.expenseTrackerApp.repository;

import com.cciliacode.expenseTrackerApp.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
