package ru.kolesnikov.votingsystem.service;

import ru.kolesnikov.votingsystem.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> getAll();

    List<Restaurant> getAllByAdminId(long adminId);

    Restaurant get(long id, long adminId);

    Restaurant create(Restaurant restaurant, long adminId);

    Restaurant update(Restaurant restaurant, long adminId);

    void delete(long id, long adminId);
//    int getAmountOfVotes(long id);
}
