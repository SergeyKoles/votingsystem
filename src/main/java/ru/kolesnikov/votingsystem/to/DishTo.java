package ru.kolesnikov.votingsystem.to;

public class DishTo {

    public DishTo(String name, Long price) {
        this.name = name;
        this.price = price;
    }

    private String name;
    private Long price;

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
