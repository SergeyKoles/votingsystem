package ru.kolesnikov.votingsystem.service;

import org.springframework.data.jpa.repository.Query;
import ru.kolesnikov.votingsystem.model.Vote;

import java.util.List;

public interface VoteService {
    List<Vote> getAllByRestaurantId(long restaurantId);
//    List<Vote> getAllByUserId(long userId);
//    Vote get(long id);
    Vote save(Vote vote);
//    Vote update(Vote vote);
    void deleteAll();
//    void delete(long userId);
    void update(Vote vote);
}
