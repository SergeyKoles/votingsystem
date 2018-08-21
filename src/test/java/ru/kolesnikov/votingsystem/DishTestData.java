package ru.kolesnikov.votingsystem;

import ru.kolesnikov.votingsystem.model.Dish;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.kolesnikov.votingsystem.model.AbstractBaseEntity.START_SEQ;

public class DishTestData {

    public static final long MORGARITA_ID = START_SEQ + 6;
    public static final long PEPERONI_ID = START_SEQ + 7;
    public static final long CEZAR_ID = START_SEQ + 8;
    public static final long PLAIN_ID = START_SEQ + 9;

    public static final Dish MORGARITA = new Dish(MORGARITA_ID,"Pizza_Morgarita", 300L);
    public static final Dish PEPERONI = new Dish(PEPERONI_ID,"Pizza_Peperoni", 350L);
    public static final Dish CEZAR = new Dish(CEZAR_ID,"Blin_Cezar", 200L);
    public static final Dish PLAIN = new Dish(PLAIN_ID,"Blin_Plain", 200L);

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected,"restaurant");
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }
}
