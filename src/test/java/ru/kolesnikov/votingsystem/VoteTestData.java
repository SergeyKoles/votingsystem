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

    public static final Vote VOTE_USER_A = new Vote(VOTE_USER_A_ID, USER_A_ID, DODO_ID);
    public static final Vote VOTE_USER_B = new Vote(VOTE_USER_B_ID, USER_B_ID, DODO_ID);
    public static final Vote VOTE_USER_C = new Vote(VOTE_USER_C_ID, USER_C_ID, DODO_ID);
    public static final Vote VOTE_USER_D = new Vote(VOTE_USER_D_ID, USER_D_ID, TEREMOK_ID);
    public static final Vote VOTE_USER_E = new Vote(VOTE_USER_E_ID, USER_E_ID, LESTER_ID);
    public static final Vote VOTE_USER_F = new Vote(VOTE_USER_F_ID, USER_F_ID, LESTER_ID);
    public static final Vote VOTE_USER_G = new Vote(VOTE_USER_G_ID, USER_G_ID, KFC_ID);
    public static final Vote VOTE_ADMIN_A = new Vote(VOTE_ADMIN_A_ID, ADMIN_A_ID, DODO_ID);

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
