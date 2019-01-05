package ru.kolesnikov.votingsystem;

import ru.kolesnikov.votingsystem.model.Restaurant;
import ru.kolesnikov.votingsystem.model.Vote;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.kolesnikov.votingsystem.RestaurantTestData.*;
import static ru.kolesnikov.votingsystem.UserTestData.*;
import static ru.kolesnikov.votingsystem.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {
    public static final long VOTE_USER_A_ID = START_SEQ + 33;
    public static final long VOTE_USER_B_ID = START_SEQ + 34;
    public static final long VOTE_USER_C_ID = START_SEQ + 35;
    public static final long VOTE_USER_D_ID = START_SEQ + 36;
    public static final long VOTE_USER_E_ID = START_SEQ + 37;
    public static final long VOTE_USER_F_ID = START_SEQ + 38;
    public static final long VOTE_USER_G_ID = START_SEQ + 39;
    public static final long VOTE_ADMIN_A_ID = START_SEQ + 40;

    public static Vote VOTE_USER_A = new Vote(VOTE_USER_A_ID);
    public static Vote VOTE_USER_B = new Vote(VOTE_USER_B_ID);
    public static Vote VOTE_USER_C = new Vote(VOTE_USER_C_ID);
    public static Vote VOTE_USER_D = new Vote(VOTE_USER_D_ID);
    public static Vote VOTE_USER_E = new Vote(VOTE_USER_E_ID);
    public static Vote VOTE_USER_F = new Vote(VOTE_USER_F_ID);
    public static Vote VOTE_USER_G = new Vote(VOTE_USER_G_ID);
    public static Vote VOTE_ADMIN_A = new Vote(VOTE_ADMIN_A_ID);

    static {
        VOTE_USER_A.setUser(USER_A);
        VOTE_USER_A.setRestaurant(DODO);

        VOTE_USER_B.setUser(USER_B);
        VOTE_USER_B.setRestaurant(DODO);

        VOTE_USER_C.setUser(USER_C);
        VOTE_USER_C.setRestaurant(DODO);

        VOTE_USER_D.setUser(USER_D);
        VOTE_USER_D.setRestaurant(TEREMOK);

        VOTE_USER_E.setUser(USER_E);
        VOTE_USER_E.setRestaurant(LESTER);

        VOTE_USER_G.setUser(USER_F);
        VOTE_USER_G.setRestaurant(LESTER);

        VOTE_USER_G.setUser(USER_G);
        VOTE_USER_G.setRestaurant(KFC);
    }

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(long actual, long expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).isEqualTo(expected);
//        assertThat(actual).usingElementComparatorIgnoringFields("admin").isEqualTo(expected);
    }

}
