package ru.kolesnikov.votingsystem.repository.mock;

import org.springframework.stereotype.Repository;
import ru.kolesnikov.votingsystem.UserTestData;
import ru.kolesnikov.votingsystem.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.kolesnikov.votingsystem.UserTestData.*;

@Repository
public class InMemoryUserRepo {

    private Map<Long, User> repository = new ConcurrentHashMap<>();

    public void init() {
        repository.clear();
        repository.put(USER_A_ID, USER_A);
        repository.put(USER_B_ID, USER_B);
        repository.put(ADMIN_A_ID, ADMIN_A);
        repository.put(ADMIN_B_ID, ADMIN_B);
    }
}
