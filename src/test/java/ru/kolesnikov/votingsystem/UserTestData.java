package ru.kolesnikov.votingsystem;

import ru.kolesnikov.votingsystem.model.Role;
import ru.kolesnikov.votingsystem.model.User;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.kolesnikov.votingsystem.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {

    public static final long USER_A_ID = START_SEQ;
    public static final long USER_B_ID = START_SEQ + 1;
    public static final long USER_C_ID = START_SEQ + 2;
    public static final long USER_D_ID = START_SEQ + 3;
    public static final long USER_E_ID = START_SEQ + 4;
    public static final long USER_F_ID = START_SEQ + 5;
    public static final long USER_G_ID = START_SEQ + 6;
    public static final long ADMIN_A_ID = START_SEQ + 7;
    public static final long ADMIN_B_ID = START_SEQ + 8;

    public static final User USER_A = new User(USER_A_ID, "user_A@yandex.ru", "password_A", Role.USER);
    public static final User USER_B = new User(USER_B_ID, "user_B@yandex.ru", "password_B", Role.USER);
    public static final User USER_C = new User(USER_C_ID, "user_C@yandex.ru", "password_C", Role.USER);
    public static final User USER_D = new User(USER_D_ID, "user_D@yandex.ru", "password_D", Role.USER);
    public static final User USER_E = new User(USER_E_ID, "user_E@yandex.ru", "password_E", Role.USER);
    public static final User USER_F = new User(USER_F_ID, "user_F@yandex.ru", "password_F", Role.USER);
    public static final User USER_G = new User(USER_G_ID, "user_G@yandex.ru", "password_G", Role.USER);
    public static final User ADMIN_A = new User(ADMIN_A_ID, "admin_A@yandex.ru", "password_admin_A", Role.ADMIN);
    public static final User ADMIN_B = new User(ADMIN_B_ID, "admin_B@yandex.ru", "password_admin_B", Role.ADMIN);

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
