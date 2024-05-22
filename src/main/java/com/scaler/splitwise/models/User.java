package com.scaler.splitwise.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "users") // To mark model for table creation
public class User extends BaseModel
{
    private String userName;
    private String phoneNumber;
    private String password;

    @Override
    public String toString()
    {
        return "User{" +
                "id='" + getId() + '\'' +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
