package com.scaler.splitwise.controllers;

import com.scaler.splitwise.dtos.RegisterUserRequestDto;
import com.scaler.splitwise.dtos.RegisterUserResponseDto;
import com.scaler.splitwise.dtos.Response;
import com.scaler.splitwise.exceptions.InvalidRequestException;
import com.scaler.splitwise.models.User;
import com.scaler.splitwise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController
{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public RegisterUserResponseDto registerUser(RegisterUserRequestDto requestDto)
    {
        RegisterUserResponseDto responseDto = new RegisterUserResponseDto();

        try
        {
            validateRequestDTO(requestDto);
            User user = this.userService.registerUser(requestDto.getUserName(), requestDto.getPassword(), requestDto.getPhoneNumber());
            responseDto.setUser(user);
            responseDto.setResponse(Response.getSuccessResponse());
        }
        catch (Exception ex)
        {
            responseDto.setResponse(Response.getFailureResponse(ex.getMessage()));
        }

        return responseDto;
    }

    public void validateRequestDTO(RegisterUserRequestDto requestDTO) throws InvalidRequestException
    {
        if(requestDTO.getUserName() == null || requestDTO.getPassword() == null || requestDTO.getPhoneNumber() == null) {
            throw new InvalidRequestException("Username, Password or Phone Number cannot be empty");
        }
    }
}
