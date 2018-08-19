package ru.kolesnikov.votingsystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.kolesnikov.votingsystem.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {

    @Query("SELECT r FROM Restaurant r WHERE r.id=:id AND r.admin.id=:adminId")
    Optional<Restaurant> get(@Param("id") long id, @Param("adminId") long adminId);

    @Query("SELECT r FROM Restaurant r WHERE r.admin.id=:adminId")
    List<Restaurant> getAllByAdminId(@Param("adminId") long adminId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Restaurant r WHERE r.id=:id AND r.admin.id=:adminId")
    int delete(@Param("id") long id, @Param("adminId") long adminId);
}
