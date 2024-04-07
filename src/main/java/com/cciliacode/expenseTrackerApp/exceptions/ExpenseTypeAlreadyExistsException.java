package com.cciliacode.expenseTrackerApp.exceptions;

public class ExpenseTypeAlreadyExistsException extends Exception{
    public ExpenseTypeAlreadyExistsException(String message){
        super(message);
    }
}
