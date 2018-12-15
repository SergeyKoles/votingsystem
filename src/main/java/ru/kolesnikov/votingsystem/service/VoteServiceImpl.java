package ru.kolesnikov.votingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kolesnikov.votingsystem.model.Vote;
import ru.kolesnikov.votingsystem.repository.VoteRepo;

import java.time.LocalTime;
import java.util.List;

import static ru.kolesnikov.votingsystem.util.VoteUtil.DEAD_LINE_OF_VOTING;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepo voteRepo;

    @Override
    public List<Vote> getAllByRestaurantId(long restaurantId) {
        return voteRepo.getAllByRestaurantId(restaurantId);
    }

    @Transactional
    @Override
    public Vote save(Vote vote) {
        if (DEAD_LINE_OF_VOTING.isAfter(LocalTime.now()) && vote.isNew()) {
            return voteRepo.save(vote);
        }
        return null;
    }

    @Override
    public void deleteAll() {
        voteRepo.deleteAll();
    }

    @Override
    public void update(Vote vote) {
        if (DEAD_LINE_OF_VOTING.isAfter(LocalTime.now()) && !vote.isNew()) {
//            voteRepo.
        }
    }
}
