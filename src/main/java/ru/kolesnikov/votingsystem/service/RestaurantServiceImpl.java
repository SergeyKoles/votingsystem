package ru.kolesnikov.votingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kolesnikov.votingsystem.model.Restaurant;
import ru.kolesnikov.votingsystem.repository.RestaurantRepo;
import ru.kolesnikov.votingsystem.repository.UserRepo;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepo restaurantRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepo.findAll();
    }

    @Override
    public List<Restaurant> getAllByAdminId(long adminId) {
        return restaurantRepo.getAllByAdminId(adminId);
    }

    @Override
    public Restaurant get(long id, long adminId) {
        return restaurantRepo.get(id, adminId).orElse(null);
    }

    @Override
    public Restaurant create(Restaurant restaurant, long adminId) {
        if (!restaurant.isNew() && get(restaurant.getId(), adminId) == null) {
            return null;
        }
        restaurant.setAdmin(userRepo.getOne(adminId));
        return restaurantRepo.save(restaurant);
    }

    @Override
    public Restaurant update(Restaurant restaurant, long adminId) {
        if (!restaurant.isNew() && get(restaurant.getId(), adminId) != null) {
            return restaurantRepo.save(restaurant);
        }
        return null;
    }

    @Override
    public void delete(long id, long adminId) {
        restaurantRepo.delete(id, adminId);
    }
}
