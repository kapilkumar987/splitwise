package com.scaler.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ExpenseUser extends BaseModel // mapping table
{
    @ManyToOne
    private Expense expense; // expense_id

    @ManyToOne
    private User user; // user_id

    private double amount; // This amount can be either amount owed or paid that we can judge by expenseType

    @Enumerated(value = EnumType.ORDINAL)
    private ExpenseType expenseType;

}

// Each Expense has two types of users:
// Some members whose are paying as the part of the expense second some members whose are owing as the part of the expense.
// Both these users they need to be track so that when we are settling up the expenses and the users you have all the data.

// If there is the Expense having 2 people are paying and 3 people are owing how many entries for that in this table = 5.
