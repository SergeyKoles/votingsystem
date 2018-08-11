package ru.kolesnikov.votingsystem.model;

import java.time.LocalDateTime;

public class Vote extends AbstractBaseEntity{
    private User user;
    private Restaurant restaurant;
    private LocalDateTime timeOfVoting;

    public Vote(Long id, User user, Restaurant restaurant) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
        this.timeOfVoting = LocalDateTime.now();
    }
}
