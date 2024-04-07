package com.cciliacode.expenseTrackerApp.repository;

import com.cciliacode.expenseTrackerApp.entity.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseTypeRepository extends JpaRepository<ExpenseType, Long> {
}
