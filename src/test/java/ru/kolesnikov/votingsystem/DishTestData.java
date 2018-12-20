package ru.kolesnikov.votingsystem;

import ru.kolesnikov.votingsystem.model.Dish;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.kolesnikov.votingsystem.model.AbstractBaseEntity.START_SEQ;

public class DishTestData {

    public static final long MORGARITA_ID = START_SEQ + 15;
    public static final long PEPERONI_ID = START_SEQ + 16;
    public static final long CEZAR_ID = START_SEQ + 17;
    public static final long PLAIN_ID = START_SEQ + 18;
    public static final long CHICKEN_ID = START_SEQ + 19;
    public static final long HAMBURGER_ID = START_SEQ + 20;
    public static final long EGGS_ID = START_SEQ + 21;
    public static final long PORK_ID = START_SEQ + 22;
    public static final long PEPSI_ID = START_SEQ + 23;
    public static final long COCA_COLA_ID = START_SEQ + 24;
    public static final long FANTA_ID = START_SEQ + 25;
    public static final long TEA_ID = START_SEQ + 26;
    public static final long COFFEE_ID = START_SEQ + 27;
    public static final long JUICE_ID = START_SEQ + 28;
    public static final long BEER_ID = START_SEQ + 29;
    public static final long MOCHA_ID = START_SEQ + 30;
    public static final long BEFF_ID = START_SEQ + 31;
    public static final long ICE_CREAM_ID = START_SEQ + 32;

    public static final Dish MORGARITA = new Dish(MORGARITA_ID, "Pizza_Morgarita", 300L);
    public static final Dish PEPERONI = new Dish(PEPERONI_ID, "Pizza_Peperoni", 350L);
    public static final Dish CEZAR = new Dish(CEZAR_ID, "Blin_Cezar", 200L);
    public static final Dish PLAIN = new Dish(PLAIN_ID, "Blin_Plain", 100L);
    public static final Dish CHICKEN = new Dish(CHICKEN_ID, "CHICKEN", 120L);
    public static final Dish HAMBURGER = new Dish(HAMBURGER_ID, "HAMBURGER", 130L);
    public static final Dish EGGS = new Dish(EGGS_ID, "EGGS", 140L);
    public static final Dish PORK = new Dish(PORK_ID, "PORK", 150L);
    public static final Dish PEPSI = new Dish(PEPSI_ID, "PEPSI", 160L);
    public static final Dish COCA_COLA = new Dish(COCA_COLA_ID, "COCA_COLA", 170L);
    public static final Dish FANTA = new Dish(FANTA_ID, "FANTA", 180L);
    public static final Dish TEA = new Dish(TEA_ID, "TEA", 190L);
    public static final Dish COFFEE = new Dish(COFFEE_ID, "COFFEE", 210L);
    public static final Dish JUICE = new Dish(JUICE_ID, "JUICE", 220L);
    public static final Dish BEER = new Dish(BEER_ID, "BEER", 275L);
    public static final Dish MOCHA = new Dish(MOCHA_ID, "MOCHA", 290L);
    public static final Dish BEFF = new Dish(BEFF_ID, "BEFF", 260L);
    public static final Dish ICE_CREAM = new Dish(ICE_CREAM_ID, "ICE_CREAM", 270L);

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }
}
