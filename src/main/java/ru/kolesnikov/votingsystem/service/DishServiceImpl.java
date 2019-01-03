package ru.kolesnikov.votingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private DishRepo dishRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestaurantRepo restaurantRepo;

    @Override
    public List<Dish> getAllByRestaurantId(long restaurantId) {
        return dishRepo.getAllByRestaurantId(restaurantId);
    }

    @Override
    public Dish get(long id, long restaurantId) {
        return dishRepo.get(id, restaurantId);
    }

    @Transactional
    @Override
    public Dish create(Dish dish, long restaurantId, long adminId) {
        Restaurant restaurant = restaurantRepo.getOne(restaurantId);

        if (dish.isNew() &&
                restaurant != null &&
                adminId == Objects.requireNonNull(restaurant.getAdmin().getId())) {
            dish.setRestaurant(restaurantRepo.getOne(restaurantId));
            return dishRepo.save(dish);
        }
        return null;
    }

    @Override
    public Dish update(Dish dish, long restaurantId, long adminId) {
        Restaurant restaurant = restaurantRepo.get(restaurantId, adminId).orElse(null);
        if (restaurant != null &&
                !dish.isNew() &&
                dishRepo.get(Objects.requireNonNull(dish.getId()), restaurantId) != null) {
            dish.setRestaurant(restaurant);
            return dishRepo.save(dish);
        }
        return null;
    }

    @Override
    public void delete(long id, long restaurantId, long adminId) {
        Restaurant restaurant = restaurantRepo.get(restaurantId, adminId).orElse(null);
        if (restaurant != null) {
            dishRepo.delete(id, restaurantId);
        }
    }

    @Override
    public List<Dish> getAllWithRestaurants() {
        return dishRepo.getAllWithRestaurants();
    }
}
