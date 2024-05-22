package com.scaler.splitwise.services;

import com.scaler.splitwise.exceptions.InvalidUserException;
import com.scaler.splitwise.exceptions.RegisterUserException;
import com.scaler.splitwise.models.User;
import com.scaler.splitwise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService
{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserServiceImplementation(UserRepository userRepository)
    {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public User registerUser(String userName, String password, String phoneNumber) throws RegisterUserException
    {
        Optional<User> userOptional = this.userRepository.findUserByUserNameOrPhoneNumber(userName, phoneNumber);
        if(userOptional.isPresent())
        {
            throw new RegisterUserException("User already present");
        }

        User user = new User();
        user.setUserName(userName);
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setPhoneNumber(phoneNumber);
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());

        return this.userRepository.save(user);
    }

    public void login(String userName, String password) throws InvalidUserException
    {
        Optional<User> userOptional = this.userRepository.findUserByUserName(userName);
        if(userOptional.isPresent())
        {
            User user = userOptional.get();
            if(this.bCryptPasswordEncoder.matches(password, user.getPassword()))
            {
                System.out.println("Proceeded to login");
            }
            else
            {
                throw new InvalidUserException("Password is wrong, try again later");
            }
        }
        else
        {
            throw new InvalidUserException("User is not registered");
        }
    }
}
