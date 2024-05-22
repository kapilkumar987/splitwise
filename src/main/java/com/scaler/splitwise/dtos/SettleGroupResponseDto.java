package com.scaler.splitwise.dtos;

import com.scaler.splitwise.models.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleGroupResponseDto
{
    private List<Transaction> transactions;
    private Response response;
}
