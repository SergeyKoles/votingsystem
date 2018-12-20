package ru.kolesnikov.votingsystem.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kolesnikov.votingsystem.model.Vote;

import static ru.kolesnikov.votingsystem.RestaurantTestData.*;
import static ru.kolesnikov.votingsystem.UserTestData.ADMIN_A_ID;
import static ru.kolesnikov.votingsystem.UserTestData.USER_A_ID;
import static ru.kolesnikov.votingsystem.VoteTestData.*;


public class VoteServiceImplTest extends AbstractServiceTest {

    @Autowired
    private VoteService voteService;

    @Test
    public void countAllByRestaurantId() throws Exception {
        assertMatch(voteService.countAllByRestaurantId(DODO_ID), 3L);
    }


    @Test
    public void deleteAll() throws Exception {
        voteService.deleteAll();
        assertMatch(voteService.countAllByRestaurantId(DODO_ID), 0L);
    }

    @Test
    public void getByUserId() {
        assertMatch(voteService.getByUserId(USER_A_ID), VOTE_USER_A);
    }

//    change DEAD_LINE_OF_VOTING = LocalTime.of(23, 59)!!!
    @Test
    public void create() {
        Vote newVote = new Vote();
        Vote createdVote = voteService.create(newVote, DODO_ID, ADMIN_A_ID);
        assertMatch(createdVote, VOTE_ADMIN_A);
    }

    @Test
    public void update() {
        Vote updatedVote = voteService.getByUserId(USER_A_ID);
        voteService.update(updatedVote, TEREMOK_ID);
        updatedVote.setRestaurant(TEREMOK);
        assertMatch(updatedVote, voteService.getByUserId(USER_A_ID));
    }

    @Test
    public void deleteByUserId() {
        voteService.deleteByUserId(USER_A_ID);
        assertMatch(voteService.getByUserId(USER_A_ID), null);
//        assertMatch(voteService.countAllByRestaurantId(DODO_ID), 1L);
    }

}