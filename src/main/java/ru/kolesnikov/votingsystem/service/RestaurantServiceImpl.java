package ru.kolesnikov.votingsystem.service;

import org.springframework.stereotype.Service;
import ru.kolesnikov.votingsystem.model.Restaurant;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Override
    public List<Restaurant> getAll() {
        return null;
    }

    @Override
    public Restaurant get(long id) {
        return null;
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        return null;
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public int getAmountOfVotes(long id) {
        return 0;
    }
}
