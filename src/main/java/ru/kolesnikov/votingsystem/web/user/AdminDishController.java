package ru.kolesnikov.votingsystem.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.kolesnikov.votingsystem.model.Dish;
import ru.kolesnikov.votingsystem.service.DishService;
import ru.kolesnikov.votingsystem.service.RestaurantService;
import ru.kolesnikov.votingsystem.web.SecurityUtil;

import java.net.URI;

@RestController
@RequestMapping("/admin/restaurants")
public class AdminDishController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishService dishService;

    @DeleteMapping(value = "/{restId}/dishes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("restId") long restId, @PathVariable("id") long id) {
        long adminId = SecurityUtil.authAdminId();
        dishService.delete(id, restId, adminId);
    }

    @PutMapping(value = "/{restId}/dishes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish, @PathVariable("restId") long restId, @PathVariable("id") long id) {
        long adminId = SecurityUtil.authAdminId();
        dishService.update(dish, restId, adminId);
    }

    @PostMapping(value = "/{restId}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createDish(@RequestBody Dish dish, @PathVariable("restId") long restaurantId) {
        long adminId = SecurityUtil.authAdminId();
        Dish created = dishService.create(dish, restaurantId, adminId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/admin/restaurants/{restId}/dishes/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
