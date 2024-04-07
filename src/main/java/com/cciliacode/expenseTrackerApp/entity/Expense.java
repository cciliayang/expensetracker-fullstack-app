package com.cciliacode.expenseTrackerApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Data
//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Expense name cannot be null")
    private String expenseName;

    @ManyToOne
    @NotNull(message = "Expense Type can't be empty")
    private ExpenseType expenseType;


    @NotNull(message = "Expense amount cannot be null")
    private BigDecimal expenseAmount;

    @NotNull(message = "Expense date cannot be null")
    private LocalDate localDate;

    @CreationTimestamp
    private Timestamp createdDate;
}
