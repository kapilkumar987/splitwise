package com.scaler.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class GroupExpense extends BaseModel // Mapping Table
{
    @ManyToOne
    private Group group; // Group which this Expense belongs to

    @ManyToOne
    private Expense expense;
}
