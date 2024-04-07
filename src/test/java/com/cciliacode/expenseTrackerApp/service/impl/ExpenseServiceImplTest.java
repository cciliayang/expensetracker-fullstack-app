package com.cciliacode.expenseTrackerApp.service.impl;

import com.cciliacode.expenseTrackerApp.entity.Expense;
import com.cciliacode.expenseTrackerApp.entity.ExpenseType;
import com.cciliacode.expenseTrackerApp.repository.ExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ExpenseServiceImplTest {

    @InjectMocks
    private ExpenseServiceImpl expenseService;

    @Mock
    private ExpenseRepository expenseRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testCalculateTotalAmountSpent() {

        ExpenseType expenseType = new ExpenseType();
        expenseType.setExpenseType("Groceries");

        ExpenseType expenseType2 = new ExpenseType();
        expenseType.setExpenseType("Food");

        //Create list of expenses
        List<Expense> expenses = new ArrayList<>();

        expenses.add(new Expense(1L, "Expense1", expenseType2, BigDecimal.valueOf(100), LocalDate.now(), Timestamp.valueOf(LocalDateTime.now())));

        expenses.add(new Expense(2L, "Expense2", expenseType, BigDecimal.valueOf(200), LocalDate.now(), Timestamp.valueOf(LocalDateTime.now())));

        expenses.add(new Expense(3L, "Expense3", expenseType, BigDecimal.valueOf(300), LocalDate.now(), Timestamp.valueOf(LocalDateTime.now())));

        //mock behaviour of expense repo
        when(expenseRepository.findAll()).thenReturn(expenses);

        BigDecimal totalAmount = expenseService.calculateTotalAmountSpent();

        //verify total amount spent is correct
        assertEquals(new BigDecimal("600"), totalAmount);

    }

//    @Test
//    void calculateTotalAmountSpentByCategory() {
//        ExpenseType expenseType = new ExpenseType();
//        expenseType.setExpenseType("Groceries");
//
//        ExpenseType expenseType2 = new ExpenseType();
//        expenseType.setExpenseType("Entertainment");
//
//        Expense expense1 = new Expense(1L, "Expense1", expenseType, BigDecimal.valueOf(30), LocalDate.now(), Timestamp.valueOf(LocalDateTime.now()));
//
//        Expense expense2 = new Expense(2L, "Expense2", expenseType2, BigDecimal.valueOf(10), LocalDate.now(), Timestamp.valueOf(LocalDateTime.now()));
//
//        Expense expense3 = new Expense(1L, "Expense3", expenseType, BigDecimal.valueOf(10), LocalDate.now(), Timestamp.valueOf(LocalDateTime.now()));
//
//        List<Expense> expenses = Arrays.asList(expense1, expense2, expense3);
//
//        when(expenseRepository.findAll()).thenReturn(expenses);
//
//        //Expected total amounts by category
//        Map<String, BigDecimal> expectedTotalAmountsByCategory = new HashMap<>();
//
//        expectedTotalAmountsByCategory.put("Groceries", new BigDecimal("40"));
//        expectedTotalAmountsByCategory.put("Entertainment", new BigDecimal("10"));
//
//        //call method
//        Map<String, BigDecimal> totalAmountsByCategory = expenseService.calculateTotalAmountSpentByCategory();
//
//        assertEquals(expectedTotalAmountsByCategory , totalAmountsByCategory);
//    }

    @Test
    void testCalculateTotalAmountSpentByCategory() {
        //Mocking expenses data
        ExpenseType groceries = new ExpenseType();
        groceries.setExpenseType("groceries");

        ExpenseType utilities = new ExpenseType();
        utilities.setExpenseType("utilities");

        //Create list of expenses
        List<Expense> expenses = new ArrayList<>();

        expenses.add(new Expense(1L, "Internet", utilities, BigDecimal.valueOf(100), LocalDate.now(), Timestamp.valueOf(LocalDateTime.now())));

        expenses.add(new Expense(2L, "Eggs", groceries, BigDecimal.valueOf(5), LocalDate.now(), Timestamp.valueOf(LocalDateTime.now())));

        expenses.add(new Expense(3L, "Banana", groceries, BigDecimal.valueOf(10), LocalDate.now(), Timestamp.valueOf(LocalDateTime.now())));

        when(expenseRepository.findAll()).thenReturn(expenses);

        Map<String, BigDecimal> result = expenseService.calculateTotalAmountSpentByCategory();

        assertEquals(2, result.size());
        assertEquals(new BigDecimal("15"), result.get("groceries"));
        assertEquals(new BigDecimal("100"), result.get("utilities"));
    }
}