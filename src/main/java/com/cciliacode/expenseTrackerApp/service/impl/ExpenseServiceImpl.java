package com.cciliacode.expenseTrackerApp.service.impl;

import com.cciliacode.expenseTrackerApp.entity.Expense;
import com.cciliacode.expenseTrackerApp.entity.ExpenseType;
import com.cciliacode.expenseTrackerApp.repository.ExpenseRepository;
import com.cciliacode.expenseTrackerApp.service.ExpenseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    /**
     *
     * @param expense - Input an expense
     * @return Save an expense
     */
    @Override
    public Expense save(Expense expense) {
        return expenseRepository.save(expense);
    }

    public void saveExpense(BigDecimal amount, String name, ExpenseType type) {
        Expense expense = new Expense();
        expense.setExpenseAmount(amount);
        expense.setExpenseName(name);
        expense.setExpenseType(type);
        expense.setLocalDate(LocalDate.now());
        expenseRepository.save(expense);
    }

    /**
     *
     * @param expenseId - Input an expenseId
     * @return update expense entity if unsuccessful throws an EntityNotFoundException
     */
    @Override
    public Expense findById(Long expenseId) {
        return expenseRepository.findById(expenseId).orElseThrow(()->new EntityNotFoundException("ExpenseId not found"));
    }

    /**
     *
     * @param pageable - specify the page number, page size and sorting to descending order
     * @return a page containing of a list of expense objects sorted by creation date
     */
    @Override
    public Page<Expense> findAll(Pageable pageable) {
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("creationDate").descending());

        return expenseRepository.findAll(pageable);
    }
    /**
     *
     * @return a list of all expenses
     */
    @Override
    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }
    /**
     *
     * @param expenseId - Input expenseId
     * @return successful deleted expense, if unsuccessful throws an EntityNotFoundException
     */
    @Override
    public void deleteById(Long expenseId) {
        Expense expenseToDelete = findById(expenseId);
        expenseRepository.delete(expenseToDelete);
    }

    @Override
    public BigDecimal calculateTotalAmountSpent() {
        //retrieve all expenses from the repository
        List<Expense> expenses = expenseRepository.findAll();

        //initialize total amount
        BigDecimal totalAmount = BigDecimal.ZERO;

        //loop through each expense and add its amount to the total
        for (Expense expense : expenses){
            totalAmount = totalAmount.add(expense.getExpenseAmount());
        }
        return totalAmount;
    }

    @Override
    public Map<String, BigDecimal> calculateTotalAmountSpentByCategory() {
        List<Expense> expenses = expenseRepository.findAll();
        Map<String, BigDecimal> totalAmountSpentByCategory = new HashMap<>();

        for (Expense expense : expenses) {
            String category = expense.getExpenseType().getExpenseType();
            BigDecimal amount = expense.getExpenseAmount();

            // Accumulate the amounts for each category
            totalAmountSpentByCategory.put(category,
                    totalAmountSpentByCategory.getOrDefault(category, BigDecimal.ZERO).add(amount));
        }
        return totalAmountSpentByCategory;}

//    @Override
//    public Map<String, BigDecimal> calculateTotalAmountSpentByCategory() {
//        //retrieve all expenses from repo
//        List<Expense>expenses = expenseRepository.findAll();
//
//        //create a map to store total amounts spent by category
//        Map<String, BigDecimal> totalAmountsByCategory = new HashMap<>();
//
//        //loop through each expense
//        for (Expense expense:expenses){
//            ExpenseType category = expense.getExpenseType();
//            BigDecimal amount = expense.getExpenseAmount();
//
//            //update total amount spent for category
//            if(totalAmountsByCategory.containsKey(category)){
//                BigDecimal totalAmount = totalAmountsByCategory.get(category);
//                totalAmount = totalAmount.add(amount);
//                totalAmountsByCategory.put(String.valueOf(category), totalAmount);
//            } else{
//                totalAmountsByCategory.put(String.valueOf(category),amount);
//            }
//        }
//        return totalAmountsByCategory;
//    }
}
