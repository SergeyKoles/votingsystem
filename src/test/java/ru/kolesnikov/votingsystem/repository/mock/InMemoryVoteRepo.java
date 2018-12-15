package ru.kolesnikov.votingsystem.repository.mock;

import ru.kolesnikov.votingsystem.model.Vote;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static ru.kolesnikov.votingsystem.VoteTestData.*;

public class InMemoryVoteRepo {
    private Map<Long, Vote> repository = new ConcurrentHashMap<>();

    public void init() {
        repository.clear();
        repository.put(VOTE_USER_A_ID, VOTE_USER_A);
        repository.put(VOTE_USER_B_ID, VOTE_USER_B);
    }
}
