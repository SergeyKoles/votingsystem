package ru.kolesnikov.votingsystem.model;


public class Dish extends AbstractBaseEntity {
    private String name;
    private Long price;

    public Dish(Long id, String name, Long price) {
        super(id);
        this.name = name;
        this.price = price;
    }
}
