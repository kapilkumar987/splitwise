package com.scaler.splitwise.services;

import com.scaler.splitwise.exceptions.InvalidRequestException;
import com.scaler.splitwise.models.Transaction;

import java.util.List;

public interface SettleUpService
{
    List<Transaction> settleGroup(int groupId) throws InvalidRequestException;
    List<Transaction> settleUser(int userId) throws InvalidRequestException;
}
