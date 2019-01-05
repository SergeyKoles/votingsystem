package ru.kolesnikov.votingsystem.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kolesnikov.votingsystem.model.Vote;

import java.util.List;

@Repository
public interface VoteRepo extends JpaRepository<Vote, Long> {

    @Transactional
    Vote getVoteByUserId(long userId);

    @Modifying
    @Transactional
    Vote save(Vote vote);

    @Modifying
    void deleteAll();

    @Transactional
    @Modifying
    void deleteVoteByUserId(long userId);

    @Transactional
    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT v FROM Vote v")
    List<Vote> getAllWithRestaurants();

    @Transactional
    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Vote> getAllByRestaurantId(long id);

    @Transactional
    @Modifying
    void deleteVoteById(long id);

    @Transactional
    long countAllByRestaurantId(long restaurantId);
}
