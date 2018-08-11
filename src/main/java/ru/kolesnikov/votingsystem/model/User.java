package ru.kolesnikov.votingsystem.model;

import java.util.List;

public class User extends AbstractBaseEntity{
    private String name;
    private Role role;
    private List<Restaurant> restaurants;
    private Vote vote;

    public User(Long id, String name, Role role) {
        super(id);
        this.name = name;
        this.role = role;
    }
}
