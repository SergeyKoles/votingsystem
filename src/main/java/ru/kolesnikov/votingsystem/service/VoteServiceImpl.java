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
    public long countAllByRestaurantId(long restaurantId) {
        return voteRepo.countAllByRestaurantId(restaurantId);
    }

    @Override
    public Vote create(Vote vote, long restaurantId, long userId) {
        if (DEAD_LINE_OF_VOTING.isAfter(LocalTime.now())) {
            if (vote.isNew()) {
                vote.setUser(userRepo.getOne(userId));
                vote.setRestaurant(restaurantRepo.getOne(restaurantId));
                return voteRepo.save(vote);
            } else {
                update(vote, restaurantId);
                return vote;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public void update(Vote vote, long restaurantId) {
//        vote.setUser(userRepo.getOne(userId));
        vote.setTimeOfVoting(LocalTime.now());
        vote.setRestaurant(restaurantRepo.getOne(restaurantId));
        voteRepo.save(vote);
    }

    @Override
    public void deleteByUserId(long userId) {
        voteRepo.deleteVoteByUserId(userId);
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
}
