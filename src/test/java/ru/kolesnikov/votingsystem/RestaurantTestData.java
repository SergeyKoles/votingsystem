package ru.kolesnikov.votingsystem;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.kolesnikov.votingsystem.model.Restaurant;
import ru.kolesnikov.votingsystem.to.RestaurantTo;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
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
    public static final Restaurant MD = new Restaurant(MD_ID, "MD") ;
    public static final Restaurant OLIS = new Restaurant(OLIS_ID, "OLIS");
    public static final Restaurant LESTER = new Restaurant(LESTER_ID, "LESTER");
    public static final Restaurant KFC = new Restaurant(KFC_ID, "KFC");

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected,"admin");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("admin").isEqualTo(expected);
    }

    public static ResultMatcher getToMatcher(RestaurantTo... expected) {
        return getToMatcher((RestaurantTo) Arrays.asList(expected));
    }

    public static ResultMatcher getToMatcher(Iterable<RestaurantTo> expected) {
        return result -> assertThat(readListFromJsonMvcResult(result, RestaurantTo.class)).isEqualTo(expected);
    }

}
