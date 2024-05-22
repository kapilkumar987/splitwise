package com.scaler.splitwise.dtos;

import com.scaler.splitwise.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserResponseDto
{
    private User user;
    private Response response;
}
