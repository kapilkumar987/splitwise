package com.scaler.splitwise.services;

import com.scaler.splitwise.exceptions.InvalidRequestException;
import com.scaler.splitwise.models.*;
import com.scaler.splitwise.repositories.GroupExpenseRepository;
import com.scaler.splitwise.repositories.GroupRepository;
import com.scaler.splitwise.strategies.SettleUpStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SettleUpServiceImplementation implements SettleUpService
{
    private final GroupRepository groupRepository;
    private final GroupExpenseRepository groupExpenseRepository;
    private final SettleUpStrategy settleUpStrategy;

    @Autowired
    public SettleUpServiceImplementation(GroupRepository groupRepository, GroupExpenseRepository groupExpenseRepository, SettleUpStrategy settleUpStrategy) {
        this.groupRepository = groupRepository;
        this.groupExpenseRepository = groupExpenseRepository;
        this.settleUpStrategy = settleUpStrategy;
    }

    @Override
    public List<Transaction> settleGroup(int groupId) throws InvalidRequestException {

        /*
        1. Validate Group ID using DB
        2. Fetch list of expenses from group expenses using group id
        3. Condense all the expenses down to user and their final total
        4. Use Strategy pattern to get the list of Transactions need to be made
         */

        Group group = this.groupRepository.findById(groupId).orElseThrow(() -> new InvalidRequestException("Invalid Group ID"));
        List<GroupExpense> groupExpenses = this.groupExpenseRepository.findAllByGroup_Id(groupId);
        List<Expense> expenses = groupExpenses.stream().map(GroupExpense:: getExpense).toList();
        Map<User, Double> usersTotal = getCondensedTotal(expenses);

        return settleUpStrategy.settleUp(usersTotal);
    }

    @Override
    public List<Transaction> settleUser(int userId) throws InvalidRequestException
    {
        /*
        1. Validate the user
        2. Fetch the list of expenses that the user is part of (query the ExpenseUser table)
        3. Condense all the expenses down to user and their final total
        4. Use Strategy pattern to get the list of Transactions need to be made
         */

        return null;
    }

    public Map<User, Double> getCondensedTotal(List<Expense> expenses)
    {
        Map<User, Double> usersTotal = new HashMap<>();
        for(Expense expense: expenses) {
            for(ExpenseUser expenseUser: expense.getExpenseUsers()) {
                User user = expenseUser.getUser();
                usersTotal.put(user, usersTotal.getOrDefault(user, 0d) +
                        (expenseUser.getExpenseType().equals(ExpenseType.PAID) ? 1 : -1) * expenseUser.getAmount());
            }
        }
        return usersTotal;
    }
}
