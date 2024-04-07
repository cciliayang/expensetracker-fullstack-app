package com.cciliacode.expenseTrackerApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Data
//@Getter
//@Setter
public class ExpenseType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Expense category cannot be empty")
    @Column(unique = true)
    private String expenseType;
}
