package ru.kolesnikov.votingsystem;

import ru.kolesnikov.votingsystem.model.Restaurant;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.kolesnikov.votingsystem.model.AbstractBaseEntity.START_SEQ;


public class RestaurantTestData {

    public static final long DODO_ID = START_SEQ + 4;
    public static final long TEREMOK_ID = START_SEQ + 5;

    public static final Restaurant DODO = new Restaurant(DODO_ID, "DODO");
    public static final Restaurant Teremok = new Restaurant(TEREMOK_ID, "Teremok");

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected,"admin");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("admin").isEqualTo(expected);
    }

}
