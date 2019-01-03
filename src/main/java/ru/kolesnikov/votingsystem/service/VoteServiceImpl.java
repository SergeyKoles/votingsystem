package ru.kolesnikov.votingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kolesnikov.votingsystem.model.Vote;
import ru.kolesnikov.votingsystem.repository.RestaurantRepo;
import ru.kolesnikov.votingsystem.repository.UserRepo;
import ru.kolesnikov.votingsystem.repository.VoteRepo;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import static ru.kolesnikov.votingsystem.util.VoteUtil.DEAD_LINE_OF_VOTING;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepo voteRepo;

    @Autowired
    private RestaurantRepo restaurantRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    @Transactional
    public Vote create(long restaurantId, long userId) {
        if (DEAD_LINE_OF_VOTING.isAfter(LocalTime.now())) {
            Vote vote = voteRepo.getVoteByUserId(userId);
            if (vote == null) {
                vote = new Vote();
            }
            vote.setTimeOfVoting(LocalTime.now());
            vote.setUser(userRepo.getOne(userId));
            vote.setRestaurant(restaurantRepo.getOne(restaurantId));
            return voteRepo.save(vote);
        }
        return null;
    }

    @Override
    public void deleteVoteById(long id, long userId) {
        Vote vote = voteRepo.getOne(id);
        if (Objects.requireNonNull(vote.getUser().getId()) == userId) {
            voteRepo.deleteVoteById(id);
        }
    }

    @Override
    public void deleteAll() {
        voteRepo.deleteAll();
    }


    @Override
    public Vote getByUserId(long userId) {
        return voteRepo.getVoteByUserId(userId);
    }

    @Override
    public List<Vote> getAllWithRestaurants() {
        return voteRepo.getAllWithRestaurants();
    }

    @Override
    public List<Vote> getAll() {
        return voteRepo.findAll();
    }

    @Override
    public List<Vote> getAllByRestaurantId(long id) {
        return voteRepo.getAllByRestaurantId(id);
    }

    @Override
    public void deleteVoteByUserId(long userId) {
        if (voteRepo.getVoteByUserId(userId) != null) {
            voteRepo.deleteVoteByUserId(userId);
        }
    }

    @Override
    public long countAllByRestaurantId(long restaurantId) {
        return voteRepo.countAllByRestaurantId(restaurantId);
    }
}
