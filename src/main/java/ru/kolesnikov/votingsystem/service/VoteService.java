package ru.kolesnikov.votingsystem.service;

import ru.kolesnikov.votingsystem.model.Vote;

import java.util.List;

public interface VoteService {
    long countAllByRestaurantId(long restaurantId);

    Vote getByUserId(long userId);

    Vote create(Vote vote, long restaurantId, long userId);

    void deleteAll();

    void update(Vote vote, long restaurantId);

    void deleteByUserId(long userId);

    List<Vote> getAllWithRestaurants();

    List<Vote> getAll();

    List<Vote> getAllByRestaurantId(long id);
}
