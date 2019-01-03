package ru.kolesnikov.votingsystem.service;

import ru.kolesnikov.votingsystem.model.Vote;

import java.util.List;

public interface VoteService {
    Vote getByUserId(long userId);

    Vote create(long restaurantId, long userId);

    void deleteAll();

    List<Vote> getAllWithRestaurants();

    List<Vote> getAll();

    List<Vote> getAllByRestaurantId(long id);

    void deleteVoteById(long id, long userId);

    void deleteVoteByUserId(long userId);

    long countAllByRestaurantId(long restaurantId);

}
