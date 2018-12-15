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
    public static final long VOTE_USER_A_ID = START_SEQ + 10;
    public static final long VOTE_USER_B_ID = START_SEQ + 11;

    public static final Vote VOTE_USER_A = new Vote(VOTE_USER_A_ID, USER_A_ID, DODO_ID);
    public static final Vote VOTE_USER_B = new Vote(VOTE_USER_B_ID, USER_B_ID, DODO_ID);

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected);
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).isEqualTo(expected);
//        assertThat(actual).usingElementComparatorIgnoringFields("admin").isEqualTo(expected);
    }

}
