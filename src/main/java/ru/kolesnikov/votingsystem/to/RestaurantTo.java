package ru.kolesnikov.votingsystem.to;

import java.util.List;

public class RestaurantTo {

    public RestaurantTo(String name, List<DishTo> dishes, long voteRate) {
        this.name = name;
        this.dishes = dishes;
        this.voteRate = voteRate;
    }

    private String name;
    private List<DishTo> dishes;
    private long voteRate;

    public String getName() {
        return name;
    }

    public List<DishTo> getDishes() {
        return dishes;
    }

    public long getVoteRate() {
        return voteRate;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "name='" + name + '\'' +
                ", dishes=" + dishes +
                ", voteRate=" + voteRate +
                '}';
    }
}
