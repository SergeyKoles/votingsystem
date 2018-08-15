package ru.kolesnikov.votingsystem.service;

import ru.kolesnikov.votingsystem.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User get(long id);
    User create(User user);
    void update(User user);
    void delete(long id);
}
