package ru.kolesnikov.votingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.kolesnikov.votingsystem.model.Restaurant;
import ru.kolesnikov.votingsystem.repository.RestaurantRepo;
import ru.kolesnikov.votingsystem.repository.UserRepo;
import ru.kolesnikov.votingsystem.to.RestaurantTo;

import java.util.List;
import java.util.Objects;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepo restaurantRepo;

    private UserRepo userRepo;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepo restaurantRepo, UserRepo userRepo) {
        this.restaurantRepo = restaurantRepo;
        this.userRepo = userRepo;
    }

    @Cacheable("restaurants")
    @Override
    public List<Restaurant> getAll() {
        return restaurantRepo.findAll();
    }

    @Override
    public Restaurant getRestaurantById(long id) {
        return restaurantRepo.getOne(id);
    }

    @Cacheable("restaurants")
    @Override
    public List<Restaurant> getAllByAdminId(long adminId) {
        return restaurantRepo.getAllByAdminId(adminId);
    }

    @Override
    public Restaurant getRestaurantByIdAndAdminId(long id, long adminId) {
        return restaurantRepo.get(id, adminId).orElse(null);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public Restaurant create(Restaurant restaurant, long adminId) {
        if (restaurant.isNew()) {
            restaurant.setAdmin(userRepo.getOne(adminId));
            return restaurantRepo.save(restaurant);
        }
        return null;
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public Restaurant update(Restaurant restaurant, long adminId) {
        if (!restaurant.isNew() && Objects.requireNonNull(restaurant.getAdmin().getId()) == adminId) {
            return restaurantRepo.save(restaurant);
        }
        return null;
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void delete(long id, long adminId) {
        restaurantRepo.deleteRestaurantByIdAndAdminId(id, adminId);
    }
}
