package ru.kolesnikov.votingsystem;

import ru.kolesnikov.votingsystem.model.Role;
import ru.kolesnikov.votingsystem.model.User;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.kolesnikov.votingsystem.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {

    public static final long USER_A_ID = START_SEQ;
    public static final long USER_B_ID = START_SEQ + 1;
    public static final long ADMIN_A_ID = START_SEQ + 2;
    public static final long ADMIN_B_ID = START_SEQ + 3;

    public static final User USER_A = new User(USER_A_ID, "user_A", Role.USER);
    public static final User USER_B = new User(USER_B_ID, "user_B", Role.USER);
    public static final User ADMIN_A = new User(ADMIN_A_ID, "admin_A", Role.ADMIN);
    public static final User ADMIN_B = new User(ADMIN_B_ID, "admin_B", Role.ADMIN);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected);
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
