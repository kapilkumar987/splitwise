package com.scaler.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Currency;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name = "expenses")
public class Expense extends BaseModel // It can be Group Expense or Non Group Expense
{

    private double amount;
    private String description;
    private Date addedAt;
    private String proofUrl; // We can also attach Image of the bill.

    @Enumerated(value = EnumType.ORDINAL)
    private Currency currency;

    @OneToMany(mappedBy = "expense")
    private List<ExpenseUser> expenseUsers;

    // One Expense has multiple paid users
}
