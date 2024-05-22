package com.scaler.splitwise.repositories;

import com.scaler.splitwise.models.GroupExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupExpenseRepository extends JpaRepository<GroupExpense, Integer>
{
    List<GroupExpense> findAllByGroup_Id(int groupId);

    // select * from group_expense where group_id = 123;
}
