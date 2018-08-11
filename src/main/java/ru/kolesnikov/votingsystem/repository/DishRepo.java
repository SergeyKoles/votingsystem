package ru.kolesnikov.votingsystem.repository;

import ru.kolesnikov.votingsystem.model.Dish;

import java.util.List;

public interface DishRepo {
    Dish get(int id);
    List<Dish> getAll();
    Dish save(Dish dish);
    boolean delete(int id);
}
