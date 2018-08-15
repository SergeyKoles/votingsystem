package ru.kolesnikov.votingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kolesnikov.votingsystem.model.User;
import ru.kolesnikov.votingsystem.repository.UserRepo;

import java.util.List;

@Service
//@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public User get(long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User create(User user) {
        return userRepo.save(user);
    }

    @Override
    public void update(User user) {
        userRepo.save(user);
    }

    @Override
    public void delete(long id) {
        userRepo.deleteById(id);
    }
}
