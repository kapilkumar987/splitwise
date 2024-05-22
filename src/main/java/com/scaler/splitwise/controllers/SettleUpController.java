package com.scaler.splitwise.controllers;

import com.scaler.splitwise.dtos.Response;
import com.scaler.splitwise.dtos.SettleGroupRequestDto;
import com.scaler.splitwise.dtos.SettleGroupResponseDto;
import com.scaler.splitwise.exceptions.InvalidRequestException;
import com.scaler.splitwise.models.Transaction;
import com.scaler.splitwise.services.SettleUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SettleUpController
{
    private final SettleUpService settleUpService;

    @Autowired
    public SettleUpController(SettleUpService settleUpService) {
        this.settleUpService = settleUpService;
    }

    public SettleGroupResponseDto settleGroup(SettleGroupRequestDto requestDto)
    {
        SettleGroupResponseDto responseDto = new SettleGroupResponseDto();

        try
        {
            if(requestDto.getGroupId() < 0)
            {
                throw new InvalidRequestException("Invalid Group ID");
            }

            List<Transaction> transactions = this.settleUpService.settleGroup(requestDto.getGroupId());
            responseDto.setTransactions(transactions);
            responseDto.setResponse(Response.getSuccessResponse());
        }
        catch (Exception ex)
        {
            responseDto.setResponse(Response.getFailureResponse(ex.getMessage()));
        }

        return responseDto;
    }
}
