package ru.kolesnikov.votingsystem.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static ru.kolesnikov.votingsystem.RestaurantTestData.*;
import static ru.kolesnikov.votingsystem.VoteTestData.*;



public class VoteServiceImplTest extends AbstractServiceTest {

    @Autowired
    private VoteService voteService;

    @Test
    public void getAllByRestaurantId() throws Exception {
       assertMatch(voteService.getAllByRestaurantId(DODO_ID), VOTE_USER_A, VOTE_USER_B);
    }

    @Test
    public void save() throws Exception {
    }

    @Test
    public void deleteAll() throws Exception {
    }

}