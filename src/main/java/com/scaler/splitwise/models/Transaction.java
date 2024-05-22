package com.scaler.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "transactions")
public class Transaction extends BaseModel
{
    @ManyToOne
    private User paidFrom;

    @ManyToOne
    private User paidTo;

    private double amount;
}
