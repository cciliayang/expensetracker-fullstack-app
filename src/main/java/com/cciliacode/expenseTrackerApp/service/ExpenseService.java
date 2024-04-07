package com.cciliacode.expenseTrackerApp.service;

import com.cciliacode.expenseTrackerApp.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ExpenseService {
    /**
     *
     * @param expense - Input an expense
     * @return Save an expense
     */
    public Expense save(Expense expense);
    /**
     *
     * @param expenseId - Input an expenseId
     * @return saved expense entity if unsuccessful throws an EntityNotFoundException
     */
    public Expense findById(Long expenseId);
    /**
     *
     * @param pageable - specify the page number, page size and sorting to descending order
     * @return a page containing of a list of expense objects sorted by creation date
     */
    public Page<Expense> findAll(Pageable pageable);
    /**
     *
     * @return a list of all expenses
     */
    public List<Expense> findAll();
    /**
     *
     * @param expenseId - Input expenseId
     * @return successful deleted expense, if unsuccessful throws an EntityNotFoundException
     */
    public void deleteById(Long expenseId);

    /**
     * Calculate total amount of all expenses
     *
     * @return total amount of expenses as a BigDecimal value
     */
    public BigDecimal calculateTotalAmountSpent();

    /**
     * Calculate total amount of all expenses by category
     *
     * @return total amount of expenses as a BigDecimal value
     */
//    public Map<String, BigDecimal> calculateTotalAmountSpentByCategory();

    public Map<String, BigDecimal> calculateTotalAmountSpentByCategory();



}
