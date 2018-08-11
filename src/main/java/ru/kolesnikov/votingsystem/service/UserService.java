package ru.kolesnikov.votingsystem.service;

import ru.kolesnikov.votingsystem.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User get(int id);
    User create(User user);
    User update(User user);
    void delete(int id);
}
