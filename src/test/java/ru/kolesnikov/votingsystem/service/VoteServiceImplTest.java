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
    public void getByUserId() {
        assertMatch(voteService.getByUserId(USER_A_ID), VOTE_USER_A);
    }

    @Test
    public void create() {
        assertMatch(voteService.countAllByRestaurantId(100009), 3);
        Vote createdVote = voteService.create(DODO_ID, ADMIN_A_ID);
        assertMatch(createdVote, VOTE_ADMIN_A);
        assertMatch(voteService.countAllByRestaurantId(100009), 4);
    }

    @Test
    public void createDoubleClick() {
        assertMatch(voteService.countAllByRestaurantId(100009), 3);
        Vote createdVote = voteService.create(DODO_ID, USER_A_ID);
        assertMatch(voteService.countAllByRestaurantId(100009), 3);
    }

    @Test
    public void update() {
        Vote updatedVote = voteService.getByUserId(USER_A_ID);
        voteService.create(TEREMOK_ID, USER_A_ID);
        updatedVote.setRestaurant(TEREMOK);
        assertMatch(updatedVote, voteService.getByUserId(USER_A_ID));
        assertMatch(voteService.countAllByRestaurantId(100010), 3);
        assertMatch(voteService.countAllByRestaurantId(100009), 2);

    }

    @Test
    public void delete() {
        voteService.deleteVoteByUserId(USER_A_ID);
        assertMatch(voteService.countAllByRestaurantId(100009), 2);
        voteService.deleteVoteByUserId(USER_A_ID);
        assertMatch(voteService.countAllByRestaurantId(100009), 2);
    }

    @Test
    public void resetVotes(){
        voteService.deleteAll();
        assertMatch(voteService.countAllByRestaurantId(100009), 0);
        assertMatch(voteService.countAllByRestaurantId(100010), 0);
    }

    @Test
    public void countAllByRestaurantId() {
        assertMatch(voteService.countAllByRestaurantId(100009), 3);
    }
}