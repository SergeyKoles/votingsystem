package ru.kolesnikov.votingsystem.repository.mock;

import org.springframework.stereotype.Repository;
import ru.kolesnikov.votingsystem.model.Restaurant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static ru.kolesnikov.votingsystem.RestaurantTestData.*;

@Repository
public class InMemoryRestaurantRepo {

    private Map<Long, Restaurant> repository = new ConcurrentHashMap<>();

    public void init() {
        repository.clear();
        repository.put(DODO_ID, DODO);
        repository.put(TEREMOK_ID, TEREMOK);
    }
}
