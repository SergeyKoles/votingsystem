package ru.kolesnikov.votingsystem.service;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.ExternalResource;
import org.junit.rules.Stopwatch;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.kolesnikov.votingsystem.TimingRules;
import ru.kolesnikov.votingsystem.model.Role;
import ru.kolesnikov.votingsystem.model.User;

import java.util.List;

import static ru.kolesnikov.votingsystem.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceImplTest {

    @ClassRule
    public static ExternalResource summary = TimingRules.SUMMARY;

    @Rule
    public Stopwatch stopwatch = TimingRules.STOPWATCH;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    static {
        // needed only for java.util.logging (postgres driver)
        SLF4JBridgeHandler.install();
    }

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
        assertMatch(all, USER_A, USER_B, ADMIN_A, ADMIN_B);
    }


        @Test
    public void create() throws Exception {
        User newUser = new User(null, "new", Role.USER);
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(service.getAll(), USER_A, USER_B, ADMIN_A, ADMIN_B, newUser);
    }

    @Test
    public void update() throws Exception {
        User updated = new User(USER_A);
        updated.setName("Updated");
        service.update(updated);
        assertMatch(service.get(USER_A_ID), updated);
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER_A_ID);
        assertMatch(service.getAll(), USER_B, ADMIN_A, ADMIN_B);
    }
}