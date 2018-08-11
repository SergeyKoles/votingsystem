package ru.kolesnikov.votingsystem.service;

import ru.kolesnikov.votingsystem.model.Vote;

import java.util.List;

public interface VoteService {
    List<Vote> getAll();
    Vote get(int id);
    Vote create(Vote vote);
    Vote update(Vote vote);
    void delete(int id);
}
