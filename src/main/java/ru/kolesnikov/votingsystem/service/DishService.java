package ru.kolesnikov.votingsystem.service;

import ru.kolesnikov.votingsystem.model.Dish;

import java.util.List;

public interface DishService {
    List<Dish> getAllByRestaurantId(long restaurantId);

    Dish get(long id, long restaurantId);

    Dish create(Dish dish, long restaurantId);

    Dish update(Dish dish, long restaurantId);

    void delete(long id, long restaurantId);
}
