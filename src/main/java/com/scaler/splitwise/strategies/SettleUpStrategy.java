package com.scaler.splitwise.strategies;

import com.scaler.splitwise.models.Transaction;
import com.scaler.splitwise.models.User;

import java.util.List;
import java.util.Map;

public interface SettleUpStrategy
{
    List<Transaction> settleUp(Map<User, Double> usersTotal);
}
