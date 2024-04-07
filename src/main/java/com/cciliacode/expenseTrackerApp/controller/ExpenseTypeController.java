package com.cciliacode.expenseTrackerApp.controller;

import com.cciliacode.expenseTrackerApp.entity.ExpenseType;
import com.cciliacode.expenseTrackerApp.repository.ExpenseTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api")
public class ExpenseTypeController {

    @Autowired
    private ExpenseTypeRepository expenseTypeRepository;

    @GetMapping("/addCategory")
    public String showAddCategoryForm(){
        return "addCategory";
    }

    @PostMapping("/addCategory")
    public String addCategory(@RequestParam("expenseType") String expenseType){
        ExpenseType newExpenseType = new ExpenseType();
        newExpenseType.setExpenseType(expenseType);
        expenseTypeRepository.save(newExpenseType);
        return "redirect:/api/addCategory";
    }

    @GetMapping("/categories")
    public String showCategories(Model model){
        List<ExpenseType> categories = expenseTypeRepository.findAll();
        model.addAttribute("categories", categories);
        return "categories";
    }
}
