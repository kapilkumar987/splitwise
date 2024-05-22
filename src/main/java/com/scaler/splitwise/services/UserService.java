package com.scaler.splitwise.services;

import com.scaler.splitwise.exceptions.RegisterUserException;
import com.scaler.splitwise.models.User;

public interface UserService
{
    User registerUser(String userName, String password, String phoneNumber) throws RegisterUserException;
}
