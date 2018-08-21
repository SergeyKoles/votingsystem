package ru.kolesnikov.votingsystem.repository.mock;

import ru.kolesnikov.votingsystem.model.Dish;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static ru.kolesnikov.votingsystem.DishTestData.*;

public class InMemoryDishRepo {

    private Map<Long, Dish> repository = new ConcurrentHashMap<>();

    public void init() {
        repository.clear();
        repository.put(MORGARITA_ID, MORGARITA);
        repository.put(PEPERONI_ID, PEPERONI);
        repository.put(CEZAR_ID, CEZAR);
        repository.put(PLAIN_ID, PLAIN);
    }
}
