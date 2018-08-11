package ru.kolesnikov.votingsystem.repository;

import ru.kolesnikov.votingsystem.model.User;

import java.util.List;

public interface UserRepo {
    User get(int id);
    List<User> getAll();
    User save(User user);
    boolean delete(int id);
}
