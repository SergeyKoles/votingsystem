package ru.kolesnikov.votingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.kolesnikov.votingsystem.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> getRestaurantByIdAndAdminId(@Param("id") long id, @Param("adminId") long adminId);

    List<Restaurant> getAllByAdminId(long adminId);

    @Modifying
    @Transactional
    void deleteRestaurantByIdAndAdminId(long id, long adminId);
}
