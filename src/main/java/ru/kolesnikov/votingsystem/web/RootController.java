package ru.kolesnikov.votingsystem.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.kolesnikov.votingsystem.model.Dish;
import ru.kolesnikov.votingsystem.model.Restaurant;
import ru.kolesnikov.votingsystem.model.Vote;
import ru.kolesnikov.votingsystem.service.DishService;
import ru.kolesnikov.votingsystem.service.RestaurantService;
import ru.kolesnikov.votingsystem.service.VoteService;
import ru.kolesnikov.votingsystem.to.RestaurantTo;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.kolesnikov.votingsystem.util.RestaurantUtil.*;

@RestController
public class RootController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private DishService dishService;

    @GetMapping(value = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantTo> getAllWithRateAndMenu() {

        List<Dish> dishes = dishService.getAllWithRestaurants();
        List<Vote> votes = voteService.getAllWithRestaurants();

        return getAllWithRateAndDishes(votes, dishes);
    }

    @GetMapping(value = "restaurants/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestaurantTo getRestaurantByIdWithMenuAndRate(@PathVariable("id") long id){
        List<Dish> dishes = dishService.getAllByRestaurantId(id);
        List<Vote> votes = voteService.getAllByRestaurantId(id);

        return getWithRateAndDishes(votes,dishes);
    }

    @GetMapping(value = "/restaurants/{id}/votes")
    public long countVotesByRestaurantId(@PathVariable("id") long id){
        return voteService.countAllByRestaurantId(id);
    }
}
