package com.scaler.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "groups")
public class Group extends BaseModel
{
    private String name;
    private String description;

    @ManyToMany
    private List<User> participants;

    @ManyToMany
    private List<User> admins;
}
