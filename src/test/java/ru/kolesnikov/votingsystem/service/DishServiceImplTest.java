package ru.kolesnikov.votingsystem.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import ru.kolesnikov.votingsystem.model.Dish;

import java.util.List;

import static ru.kolesnikov.votingsystem.DishTestData.*;
import static ru.kolesnikov.votingsystem.RestaurantTestData.*;
import static ru.kolesnikov.votingsystem.UserTestData.*;

public class DishServiceImplTest extends AbstractServiceTest {

    @Autowired
    private DishService dishService;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("dishes").clear();
    }

    @Test
    public void getAllByRestaurantId() throws Exception {
        List<Dish> dishes = dishService.getAllByRestaurantId(DODO_ID);
        assertMatch(dishes, MORGARITA, PEPERONI, CEZAR);
    }

    @Test
    public void get() throws Exception {
        assertMatch(dishService.get(PEPERONI_ID, DODO_ID), PEPERONI);
    }

    @Test
    public void create() throws Exception {
        Dish newDish = new Dish(null, "4Cheese", 270L);
        Dish created = dishService.create(newDish, DODO_ID, ADMIN_A_ID);
        newDish.setId(created.getId());
        assertMatch(dishService.getAllByRestaurantId(DODO_ID), MORGARITA, PEPERONI, CEZAR, newDish);
    }

    @Test
    public void update() throws Exception {
        Dish updated = new Dish(CEZAR_ID, "Cezar_Tomat", 230L);
        updated.setRestaurant(DODO);
        dishService.update(updated, DODO_ID, ADMIN_A_ID);
        assertMatch(dishService.get(CEZAR_ID, DODO_ID), updated);
    }

    @Test
    public void delete() throws Exception {
        dishService.delete(PLAIN_ID, TEREMOK_ID, ADMIN_A_ID);
        assertMatch(dishService.get(PLAIN_ID,TEREMOK_ID), PLAIN);
//        assertMatch(dishService.getAllByRestaurantId(TEREMOK_ID), CHICKEN, HAMBURGER);
    }

}