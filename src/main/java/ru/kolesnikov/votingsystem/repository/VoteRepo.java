package ru.kolesnikov.votingsystem.repository;

import ru.kolesnikov.votingsystem.model.Vote;

import java.util.List;

public interface VoteRepo {
    Vote get(int id);
    List<Vote> getAll();
    Vote save(Vote vote);
    boolean delete(int id);
}
