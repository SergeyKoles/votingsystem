package ru.kolesnikov.votingsystem.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.kolesnikov.votingsystem.model.Dish;

import java.util.List;

public interface DishRepo extends JpaRepository<Dish, Long> {

    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Dish> getAllByRestaurantId(long restaurantId);

    Dish getDishByIdAndRestaurantId(long id,long restaurantId);

    @Modifying
    @Transactional
    void deleteDishByIdAndRestaurantId(long id, long restaurantId);

    @Transactional
    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT d FROM Dish d")
    List<Dish> getAllWithRestaurants();
}
