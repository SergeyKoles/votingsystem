package ru.kolesnikov.votingsystem.util;

import ru.kolesnikov.votingsystem.model.Dish;
import ru.kolesnikov.votingsystem.model.Restaurant;
import ru.kolesnikov.votingsystem.model.Vote;
import ru.kolesnikov.votingsystem.to.DishTo;
import ru.kolesnikov.votingsystem.to.RestaurantTo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;



public class RestaurantUtil {

    private RestaurantUtil() {
    }

    public static RestaurantTo getWithRateAndDishes(List<Vote> votes, List<Dish> dishes) {
        return getAllWithRateAndDishes(votes,dishes).get(0);
    }

    public static List<RestaurantTo> getAllWithRateAndDishes(List<Vote> votes, List<Dish> dishes) {

        Map<Restaurant, Long> votesSumByRestaurant = votes.stream()
                .collect(
                        Collectors.groupingBy(Vote::getRestaurant, Collectors.counting())
                );

        Map<Restaurant, List<Dish>> dishesSumByRestaurant = dishes.stream()
                .collect(Collectors.groupingBy(Dish::getRestaurant));

        return votes.stream()
                .filter(distinctByKey(Vote::getRestaurant))
                .map(Vote::getRestaurant)
                .collect(Collectors.toList())
                .stream()
                .map(rest -> createRestaurantTo(rest.getId(),
                        rest.getName(),
                        dishTos(dishesSumByRestaurant.get(rest)),
                        votesSumByRestaurant.get(rest)))
                .collect(Collectors.toList());
    }

    private static RestaurantTo createRestaurantTo(long id, String name, List<DishTo> dishes, long rate) {
        return new RestaurantTo(id, name, dishes, rate);
    }

    private static List<DishTo> dishTos(List<Dish> dishes) {
        return dishes.stream().map(dish -> createDishTo(dish)).collect(Collectors.toList());
    }

    private static DishTo createDishTo(Dish dish) {
        return new DishTo(dish.getId(), dish.getName(), dish.getPrice());
    }

    // https://stackoverflow.com/questions/23699371/java-8-distinct-by-property
    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
