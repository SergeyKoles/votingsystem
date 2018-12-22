package ru.kolesnikov.votingsystem.to;

import java.util.List;

public class RestaurantTo {

    public RestaurantTo(String name, List<DishTo> dishes, long voteRate) {
        this.name = name;
        this.dishes = dishes;
        this.voteRate = voteRate;
    }

    public RestaurantTo(){}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestaurantTo to = (RestaurantTo) o;

        if (voteRate != to.voteRate) return false;
        if (!name.equals(to.name)) return false;
        return dishes.equals(to.dishes);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + dishes.hashCode();
        result = 31 * result + (int) (voteRate ^ (voteRate >>> 32));
        return result;
    }
}
