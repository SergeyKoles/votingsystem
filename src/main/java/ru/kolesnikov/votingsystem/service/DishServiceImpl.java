package ru.kolesnikov.votingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kolesnikov.votingsystem.model.Dish;
import ru.kolesnikov.votingsystem.model.Restaurant;
import ru.kolesnikov.votingsystem.model.User;
import ru.kolesnikov.votingsystem.repository.DishRepo;
import ru.kolesnikov.votingsystem.repository.RestaurantRepo;
import ru.kolesnikov.votingsystem.repository.UserRepo;

import java.util.List;
import java.util.Objects;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepo dishRepo;

    private final RestaurantRepo restaurantRepo;

    @Autowired
    public DishServiceImpl(DishRepo dishRepo, RestaurantRepo restaurantRepo) {
        this.dishRepo = dishRepo;
        this.restaurantRepo = restaurantRepo;
    }

    @Cacheable("dishes")
    @Override
    public List<Dish> getAllByRestaurantId(long restaurantId) {
        return dishRepo.getAllByRestaurantId(restaurantId);
    }

    @Override
    public Dish get(long id, long restaurantId) {
        return dishRepo.getDishByIdAndRestaurantId(id, restaurantId);
    }

    @CacheEvict(value = "dishes", allEntries = true)
    @Transactional
    @Override
    public Dish create(Dish dish, long restaurantId, long adminId) {
        Restaurant restaurant = restaurantRepo.getRestaurantByIdAndAdminId(restaurantId, adminId).orElse(null);

        if (dish.isNew() && restaurant != null) {
            dish.setRestaurant(restaurantRepo.getOne(restaurantId));
            return dishRepo.save(dish);
        }
        return null;
    }

    @CacheEvict(value = "dishes", allEntries = true)
    @Override
    public Dish update(Dish dish, long restaurantId, long adminId) {
        Restaurant restaurant = restaurantRepo.getRestaurantByIdAndAdminId(restaurantId, adminId).orElse(null);
        if (restaurant != null && !dish.isNew()) {
            dish.setRestaurant(restaurant);
            return dishRepo.save(dish);
        }
        return null;
    }

    @CacheEvict(value = "dishes", allEntries = true)
    @Override
    public void delete(long id, long restaurantId, long adminId) {
        Restaurant restaurant = restaurantRepo.getRestaurantByIdAndAdminId(restaurantId, adminId).orElse(null);
        if (restaurant != null) {
            dishRepo.deleteDishByIdAndRestaurantId(id, restaurantId);
        }
    }

    @Cacheable("dishes")
    @Override
    public List<Dish> getAllWithRestaurants() {
        return dishRepo.getAllWithRestaurants();
    }
}
