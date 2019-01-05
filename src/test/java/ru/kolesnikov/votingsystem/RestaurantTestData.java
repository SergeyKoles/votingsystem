package ru.kolesnikov.votingsystem;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.kolesnikov.votingsystem.model.Dish;
import ru.kolesnikov.votingsystem.model.Restaurant;
import ru.kolesnikov.votingsystem.model.Vote;
import ru.kolesnikov.votingsystem.to.RestaurantTo;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.kolesnikov.votingsystem.DishTestData.*;
import static ru.kolesnikov.votingsystem.UserTestData.*;
import static ru.kolesnikov.votingsystem.VoteTestData.*;
import static ru.kolesnikov.votingsystem.model.AbstractBaseEntity.START_SEQ;
import static ru.kolesnikov.votingsystem.web.TestUtil.readListFromJsonMvcResult;


public class RestaurantTestData {

    public static final long DODO_ID = START_SEQ + 9;
    public static final long TEREMOK_ID = START_SEQ + 10;
    public static final long MD_ID = START_SEQ + 11;
    public static final long OLIS_ID = START_SEQ + 12;
    public static final long LESTER_ID = START_SEQ + 13;
    public static final long KFC_ID = START_SEQ + 14;

    public static final Restaurant DODO = new Restaurant(DODO_ID, "DODO");
    public static final Restaurant TEREMOK = new Restaurant(TEREMOK_ID, "TEREMOK");
    public static final Restaurant MD = new Restaurant(MD_ID, "MD");
    public static final Restaurant OLIS = new Restaurant(OLIS_ID, "OLIS");
    public static final Restaurant LESTER = new Restaurant(LESTER_ID, "LESTER");
    public static final Restaurant KFC = new Restaurant(KFC_ID, "KFC");

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "admin");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("admin").isEqualTo(expected);
    }

    public static ResultMatcher getToMatcher(RestaurantTo... expected) {
        return getToMatcher(Arrays.asList(expected));
    }

    public static ResultMatcher getToMatcher(Iterable<RestaurantTo> expected) {
        return result -> assertThat(readListFromJsonMvcResult(result, RestaurantTo.class)).isEqualTo(expected);
    }

    public static ResultMatcher getToMatcherEntity(Restaurant... expected) {
        return getToMatcherEntity(Arrays.asList(expected));
    }

    public static ResultMatcher getToMatcherEntity(Iterable<Restaurant> expected) {
        return result -> assertThat(readListFromJsonMvcResult(result, Restaurant.class)).isEqualTo(expected);
    }

    public static List<Dish> DISHES_WITH_RESTAURANT = Arrays.asList(
            new Dish(MORGARITA, DODO),
            new Dish(PEPERONI, DODO),
            new Dish(CEZAR, DODO),
            new Dish(PLAIN, TEREMOK),
            new Dish(CHICKEN, TEREMOK),
            new Dish(HAMBURGER, TEREMOK),
            new Dish(EGGS, MD),
            new Dish(PORK, MD),
            new Dish(PEPSI, MD),
            new Dish(COCA_COLA, OLIS),
            new Dish(FANTA, OLIS),
            new Dish(TEA, OLIS),
            new Dish(COFFEE, LESTER),
            new Dish(JUICE, LESTER),
            new Dish(BEER, KFC),
            new Dish(MOCHA, LESTER),
            new Dish(BEFF, KFC),
            new Dish(ICE_CREAM, KFC));

    public static List<Vote> VOTES_WITH_RESTAURANTS = Arrays.asList(
            new Vote(VOTE_USER_A, USER_A, DODO),
            new Vote(VOTE_USER_B, USER_B, DODO),
            new Vote(VOTE_USER_C, USER_C, DODO),
            new Vote(VOTE_USER_D, USER_D, TEREMOK),
            new Vote(VOTE_USER_E, USER_E, TEREMOK),
            new Vote(VOTE_USER_F, USER_F, OLIS),
            new Vote(VOTE_USER_G, USER_G, KFC));
}
