package ru.kolesnikov.votingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.kolesnikov.votingsystem.model.User;

@Transactional(readOnly = true)
public interface UserRepo extends JpaRepository<User, Long> {

    User getByEmail(String email);
}
