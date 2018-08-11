package ru.kolesnikov.votingsystem.model;

import java.util.List;

public class Restaurant extends AbstractBaseEntity{
    private String name;
    private User admin;
    private List<Dish> menu;

    public Restaurant(Long id, String name, User admin, List<Dish> menu) {
        super(id);
        this.name = name;
        this.admin = admin;
        this.menu = menu;
    }
}
