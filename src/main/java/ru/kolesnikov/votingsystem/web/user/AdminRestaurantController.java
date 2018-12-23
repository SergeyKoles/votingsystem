package ru.kolesnikov.votingsystem.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.kolesnikov.votingsystem.model.Dish;
import ru.kolesnikov.votingsystem.model.Restaurant;
import ru.kolesnikov.votingsystem.service.DishService;
import ru.kolesnikov.votingsystem.service.RestaurantService;
import ru.kolesnikov.votingsystem.web.SecurityUtil;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/admin")
public class AdminRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishService dishService;

    @GetMapping(value = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return restaurantService.getAll();
    }

    @GetMapping(value = "/{adminId}/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getRestaurantsByAdminId() {
        long adminId = SecurityUtil.authAdminId();
        return restaurantService.getAllByAdminId(adminId);
    }

    @GetMapping(value = "/{adminId}/restaurants/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getRestaurantByIdAndAdminId(@PathVariable("id") long id) {
        long adminId = SecurityUtil.authAdminId();
        return restaurantService.getRestaurantByIdAndAdminId(id, adminId);
    }

    @DeleteMapping(value = "/restaurants/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteRestaurantByIdAndByAdminId(@PathVariable("id") long id) {
        long adminId = SecurityUtil.authAdminId();
        restaurantService.delete(id, adminId);
    }

    @PostMapping(value = "/restaurants", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        long adminId = SecurityUtil.authAdminId();
//        checkNew(restaurant);
        Restaurant created = restaurantService.create(restaurant, adminId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/admin/restaurant" + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/restaurants/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") long id) {
        long adminId = SecurityUtil.authAdminId();
        restaurantService.update(restaurant, adminId);
    }

}
