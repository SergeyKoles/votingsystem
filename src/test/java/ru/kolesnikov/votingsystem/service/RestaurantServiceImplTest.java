package ru.kolesnikov.votingsystem.service;


import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import ru.kolesnikov.votingsystem.model.Restaurant;

import java.util.List;

import static ru.kolesnikov.votingsystem.RestaurantTestData.*;
import static ru.kolesnikov.votingsystem.UserTestData.*;


public class RestaurantServiceImplTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("restaurants").clear();
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(), DODO, TEREMOK, MD, OLIS, LESTER, KFC);
    }

    @Test
    public void getAllByAdminId() throws Exception {
        assertMatch(service.getAllByAdminId(ADMIN_A_ID), DODO, OLIS);
    }

    @Test
    public void get() throws Exception {
        Restaurant restaurant = service.getRestaurantByIdAndAdminId(DODO_ID, ADMIN_A_ID);
        assertMatch(restaurant, DODO);
    }

    @Test
    public void create() throws Exception {
        Restaurant newRestaurant = new Restaurant(null, "Olis");
        Restaurant created = service.create(newRestaurant, ADMIN_A_ID);
        newRestaurant.setId(created.getId());
        assertMatch(service.getAll(), DODO, TEREMOK, MD, OLIS, LESTER, KFC, newRestaurant);
    }

    @Test
    public void update() throws Exception {
        Restaurant updated = new Restaurant(DODO_ID, "DODOdiscont");
        updated.setAdmin(ADMIN_A);
        service.update(updated, ADMIN_A_ID);
        assertMatch(service.getRestaurantByIdAndAdminId(DODO_ID, ADMIN_A_ID), updated);
    }

    @Test
    public void delete() throws Exception {
        service.delete(DODO_ID, ADMIN_A_ID);
        assertMatch(service.getAllByAdminId(ADMIN_B_ID), TEREMOK, MD, LESTER, KFC);
    }
}