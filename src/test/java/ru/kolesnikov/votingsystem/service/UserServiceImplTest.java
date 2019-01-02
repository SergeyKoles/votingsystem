package ru.kolesnikov.votingsystem.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kolesnikov.votingsystem.model.Role;
import ru.kolesnikov.votingsystem.model.User;

import java.util.List;

import static ru.kolesnikov.votingsystem.UserTestData.*;

public class UserServiceImplTest extends AbstractServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void get() throws Exception {
        User user = service.get(USER_A_ID);
        assertMatch(user, USER_A);
    }

    @Test
    public void getAll() throws Exception {
        List<User> all = service.getAll();
        assertMatch(all, USER_A, USER_B, USER_C, USER_D, USER_E, USER_F, USER_G, ADMIN_A, ADMIN_B);
    }


    @Test
    public void create() throws Exception {
        User newUser = new User(null, "new@yandex.ru", "password_new", Role.USER);
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(service.getAll(), USER_A, USER_B, USER_C, USER_D, USER_E, USER_F, USER_G, ADMIN_A, ADMIN_B, newUser);
    }

    @Test
    public void update() throws Exception {
        User updated = new User(USER_A);
        updated.setEmail("Updated@yandex.ru");
        service.update(updated);
        assertMatch(service.get(USER_A_ID), updated);
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER_A_ID);
        assertMatch(service.getAll(), USER_B, USER_C, USER_D, USER_E, USER_F, USER_G, ADMIN_A, ADMIN_B);
    }
}