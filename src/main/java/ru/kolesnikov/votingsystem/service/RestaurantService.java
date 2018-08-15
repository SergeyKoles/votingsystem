package ru.kolesnikov.votingsystem.service;

import ru.kolesnikov.votingsystem.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> getAll();
    Restaurant get(long id);
    Restaurant create(Restaurant restaurant);
    Restaurant update(Restaurant restaurant);
    void delete(long id);
    int getAmountOfVotes(long id);
}
