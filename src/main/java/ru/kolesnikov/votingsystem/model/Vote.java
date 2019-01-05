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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public Vote() {
        super(null);
        this.timeOfVoting = LocalTime.now();
    }

    public Vote(long id) {
        super(id);
        this.timeOfVoting = LocalTime.now();
    }

    public Vote(Vote vote, User user, Restaurant restaurant) {
        super(vote.getId());
        this.timeOfVoting = vote.getTimeOfVoting();
        this.user = user;
        this.restaurant = restaurant;
    }

    public LocalTime getTimeOfVoting() {
        return timeOfVoting;
    }

    public void setTimeOfVoting(LocalTime timeOfVoting) {
        this.timeOfVoting = timeOfVoting;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "timeOfVoting=" + timeOfVoting +
//                ", user=" + user +
//                ", restaurant=" + restaurant +
                '}';
    }
}
