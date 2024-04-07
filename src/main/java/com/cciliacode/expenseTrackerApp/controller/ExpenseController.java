package com.cciliacode.expenseTrackerApp.controller;

import com.cciliacode.expenseTrackerApp.entity.Expense;
import com.cciliacode.expenseTrackerApp.repository.ExpenseTypeRepository;
import com.cciliacode.expenseTrackerApp.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/api")
public class ExpenseController {

    @Autowired
    private final ExpenseService expenseService;

    @Autowired
    private final ExpenseTypeRepository expenseTypeRepository;

//    @GetMapping("/total-expenses")
//    public ResponseEntity<BigDecimal> getTotalExpenses(){
//        BigDecimal totalExpenses = expenseService.calculateTotalAmountSpent();
//        return new ResponseEntity<>(totalExpenses, HttpStatus.OK);
//    }

    @GetMapping("/")
    public String home(Model model){
        return "expense";
    }

    @GetMapping("/add")
    public String showExpenseForm(Model model){
        model.addAttribute("expense", new Expense());
        model.addAttribute("expenseTypes", expenseTypeRepository.findAll());
        return "addExpense";
    }

//    @PostMapping("/save")
//    public String saveExpense(@ModelAttribute("expense") Expense expense){
//        // Save expense
//        return "redirect:/api/add";
//    }

    @PostMapping("/save")
    public String saveExpense(Expense expense){
        if (expense.getLocalDate() == null){
            expense.setLocalDate(LocalDate.now());
        }
        expenseService.save(expense);
        return "redirect:/api/add";
    }


    @GetMapping("/viewTotalExpenses")
    public String viewTotalExpenses(Model model) {
        List<Expense> expenses = expenseService.findAll();
        BigDecimal totalExpenses = expenseService.calculateTotalAmountSpent();
        model.addAttribute("expenses", expenses);
        model.addAttribute("totalExpenses", totalExpenses);
        return "totalExpenses";}

    @GetMapping("/viewTotalExpensesByCat")
    public String viewTotalExpensesByCategory(Model model){
        Map<String, BigDecimal> totalExpensesByCategory = expenseService.calculateTotalAmountSpentByCategory();
        model.addAttribute("totalExpensesByCategory", totalExpensesByCategory);
        return"totalExpensesByCat.html";
    }

}
