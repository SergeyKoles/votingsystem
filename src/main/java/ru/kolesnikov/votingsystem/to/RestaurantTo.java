package ru.kolesnikov.votingsystem.to;

import java.util.List;

public class RestaurantTo {

    public RestaurantTo(long id, String name, List<DishTo> dishes, long voteRate) {
        this.id = id;
        this.name = name;
        this.dishes = dishes;
        this.voteRate = voteRate;
    }

    public RestaurantTo(){}

    private long id;
    private String name;
    private List<DishTo> dishes;
    private long voteRate;

    public long getId() {
        return id;
    }

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
                "id=" + id +
                ", name='" + name + '\'' +
                ", dishes=" + dishes +
                ", voteRate=" + voteRate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestaurantTo to = (RestaurantTo) o;

        if (id != to.id) return false;
        if (voteRate != to.voteRate) return false;
        if (!name.equals(to.name)) return false;
        return dishes.equals(to.dishes);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + dishes.hashCode();
        result = 31 * result + (int) (voteRate ^ (voteRate >>> 32));
        return result;
    }
}
