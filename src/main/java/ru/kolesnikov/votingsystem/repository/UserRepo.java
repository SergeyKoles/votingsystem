package ru.kolesnikov.votingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.kolesnikov.votingsystem.model.User;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepo extends JpaRepository<User, Long> {

//    long delete();

//    @Override
//    Optional<User> findById(Long id);

//    User get(int id);
//    List<User> getAll();
//    User save(User user);
//    boolean delete(int id);
}
