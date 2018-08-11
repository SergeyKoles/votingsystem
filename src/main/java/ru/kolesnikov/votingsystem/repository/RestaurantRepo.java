package ru.kolesnikov.votingsystem.repository;

import ru.kolesnikov.votingsystem.model.Restaurant;

import java.util.List;

public interface RestaurantRepo {
    Restaurant get(int id);
    List<Restaurant> getAll();
    Restaurant save(Restaurant restaurant);
    boolean delete(int id);
}
