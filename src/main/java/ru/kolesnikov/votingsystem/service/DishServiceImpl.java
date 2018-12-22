package ru.kolesnikov.votingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kolesnikov.votingsystem.model.Dish;
import ru.kolesnikov.votingsystem.repository.DishRepo;
import ru.kolesnikov.votingsystem.repository.RestaurantRepo;

import java.util.List;
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepo dishRepo;

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
    public Dish create(Dish dish, long restaurantId) {
        if (!dish.isNew() && get(dish.getId(), restaurantId) == null) {
            return null;
        }
        dish.setRestaurant(restaurantRepo.getOne(restaurantId));
        return dishRepo.save(dish);
    }

    @Override
    public Dish update(Dish dish, long restaurantId) {
        if (!dish.isNew() && get(dish.getId(), restaurantId) != null) {
            return dishRepo.save(dish);
        }
        return null;
    }

    @Override
    public void delete(long id, long restaurantId) {
        dishRepo.delete(id, restaurantId);
    }

    @Override
    public List<Dish> getAllWithRestaurants() {
        return dishRepo.getAllWithRestaurants();
    }
}
