package ru.kolesnikov.votingsystem.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kolesnikov.votingsystem.model.Dish;

import java.util.List;

import static ru.kolesnikov.votingsystem.DishTestData.*;
import static ru.kolesnikov.votingsystem.RestaurantTestData.*;

public class DishServiceImplTest extends AbstractServiceTest {

    @Autowired
    private DishService dishService;

    @Test
    public void getAllByRestaurantId() throws Exception {
        List<Dish> dishes = dishService.getAllByRestaurantId(DODO_ID);
        assertMatch(dishes, MORGARITA, PEPERONI);
    }

    @Test
    public void get() throws Exception {
        assertMatch(dishService.get(PEPERONI_ID, DODO_ID), PEPERONI);
    }

    @Test
    public void create() throws Exception {
        Dish newDish = new Dish(null, "4Cheese", 270L);
        Dish created = dishService.create(newDish, DODO_ID);
        newDish.setId(created.getId());
        assertMatch(dishService.getAllByRestaurantId(DODO_ID), MORGARITA, PEPERONI, newDish);
    }

    @Test
    public void update() throws Exception {
        Dish updated = new Dish(CEZAR_ID, "Cezar_Tomat", 230L);
        updated.setRestaurant(TEREMOK);
        dishService.update(updated, TEREMOK_ID);
        assertMatch(dishService.get(CEZAR_ID, TEREMOK_ID), updated);
    }

    @Test
    public void delete() throws Exception {
        dishService.delete(PLAIN_ID, TEREMOK_ID);
        assertMatch(dishService.getAllByRestaurantId(TEREMOK_ID), CEZAR);
    }

}