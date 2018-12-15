package ru.kolesnikov.votingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.kolesnikov.votingsystem.model.Vote;

import java.util.List;

public interface VoteRepo extends JpaRepository<Vote, Long> {

    List<Vote> getAllByRestaurantId(long restaurantId);

//    List<Vote> getAllByUserId(long userId);

//    @Query("IN")
//    Vote create(Vote vote, long restaurantId, long userId);

    @Modifying
    @Query("DELETE FROM Vote")
    void deleteAll();

    @Modifying
    int deleteByUserId(long userId);
}
