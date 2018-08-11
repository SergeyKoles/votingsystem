package ru.kolesnikov.votingsystem.service;

import ru.kolesnikov.votingsystem.model.Dish;

import java.util.List;

public interface DishService {
    List<Dish> getAll();
    Dish get(int id);
    Dish create(Dish dish);
    Dish update(Dish dish);
    void delete(int id);
}
