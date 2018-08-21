package ru.kolesnikov.votingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.kolesnikov.votingsystem.model.Dish;

import java.util.List;

public interface DishRepo extends JpaRepository<Dish, Long> {

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId")
    List<Dish> getAllByRestaurantId(@Param("restaurantId") long restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restaurantId")
    Dish get(@Param("id")long id, @Param("restaurantId")long restaurantId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restaurantId")
    int delete(@Param("id") long id, @Param("restaurantId") long restaurantId);
}
