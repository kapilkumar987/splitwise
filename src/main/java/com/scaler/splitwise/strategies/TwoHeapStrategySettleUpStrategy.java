package com.scaler.splitwise.strategies;

import com.scaler.splitwise.models.Transaction;
import com.scaler.splitwise.models.User;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TwoHeapStrategySettleUpStrategy implements SettleUpStrategy
{

    @Override
    public List<Transaction> settleUp(Map<User, Double> usersTotal)
    {
        PriorityQueue<Pair<User, Double>> maxHeap = new PriorityQueue<>((o1, o2) -> (int) (o1.getSecond() - o2.getSecond()));

        PriorityQueue<Pair<User, Double>> minHeap = new PriorityQueue<>(new Comparator<Pair<User, Double>>() {
            @Override
            public int compare(Pair<User, Double> o1, Pair<User, Double> o2) {
                return (int) (o2.getSecond() - o1.getSecond());
            }
        });

        for(User user: usersTotal.keySet())
        {
            if(usersTotal.get(user) > 0)
            {
                maxHeap.add(Pair.of(user, usersTotal.get(user)));
            }
            else
            {
                minHeap.add(Pair.of(user, usersTotal.get(user)));
            }
        }

        List<Transaction> transactions = new ArrayList<>();
        while(!minHeap.isEmpty() && !maxHeap.isEmpty())
        {
            Pair<User, Double> userToPayMoney = minHeap.poll();
            Pair<User, Double> userToGetMoney = maxHeap.poll();

            double amountToBeTransferred = Math.min(Math.abs(userToPayMoney.getSecond()), userToGetMoney.getSecond());
            Transaction transaction = new Transaction();
            transaction.setAmount(amountToBeTransferred);
            transaction.setPaidFrom(userToPayMoney.getFirst());
            transaction.setPaidTo(userToGetMoney.getFirst());
            transactions.add(transaction);

            if(userToGetMoney.getSecond() - amountToBeTransferred > 0)
            {
                maxHeap.add(Pair.of(userToGetMoney.getFirst(), userToGetMoney.getSecond() - amountToBeTransferred));
            }

            if(userToPayMoney.getSecond() + amountToBeTransferred < 0)
            {
                minHeap.add(Pair.of(userToPayMoney.getFirst(), userToPayMoney.getSecond() + amountToBeTransferred));
            }
        }

        return transactions;
    }
}
