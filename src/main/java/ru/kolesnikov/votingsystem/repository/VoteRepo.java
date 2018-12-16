package ru.kolesnikov.votingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.kolesnikov.votingsystem.model.Vote;

public interface VoteRepo extends JpaRepository<Vote, Long> {

    long countAllByRestaurantId(long restaurantId);

//    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId")
//    Vote getVoteByUserId(@Param("userId")long userId);
    Vote getVoteByUserId(long userId);

    @Modifying
    @Transactional
    Vote save(Vote vote);

    @Modifying
    @Query("DELETE FROM Vote")
    void deleteAll();

//    @Modifying
//    Vote deleteByUserId(long userId);

    @Transactional
    @Modifying
    void deleteVoteByUserId(long userId);
}
