package ru.kolesnikov.votingsystem.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {

    @Column(name = "time_of_voting")
    @NotNull
    private LocalTime timeOfVoting;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(Long id, @NotNull long userId, @NotNull long restaurantId) {
        super(id);
        this.timeOfVoting = LocalTime.now();
    }

    public LocalTime getTimeOfVoting() {
        return timeOfVoting;
    }

    public void setTimeOfVoting(LocalTime timeOfVoting) {
        this.timeOfVoting = timeOfVoting;
    }
}
