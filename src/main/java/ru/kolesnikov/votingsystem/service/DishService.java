package ru.kolesnikov.votingsystem.service;

import org.springframework.stereotype.Repository;
import ru.kolesnikov.votingsystem.model.Dish;

import java.util.List;

public interface DishService {
    List<Dish> getAllByRestaurantId(long restaurantId);

    Dish get(long id, long restaurantId);

    Dish create(Dish dish, long restaurantId, long adminId);

    Dish update(Dish dish, long restaurantId, long adminId);

    void delete(long id, long restaurantId, long adminId);

    List<Dish> getAllWithRestaurants();
}
