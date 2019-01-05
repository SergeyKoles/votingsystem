package ru.kolesnikov.votingsystem.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.kolesnikov.votingsystem.model.Restaurant;
import ru.kolesnikov.votingsystem.service.DishService;
import ru.kolesnikov.votingsystem.service.RestaurantService;
import ru.kolesnikov.votingsystem.web.SecurityUtil;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRestaurantController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishService dishService;

    @GetMapping(value = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        log.info("getAll restaurants");
        return restaurantService.getAll();
    }

    @GetMapping(value = "/{adminId}/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getRestaurantsByAdminId() {
        long adminId = SecurityUtil.authUserId();
        log.info("getAll restaurants for admin {}", adminId);
        return restaurantService.getAllByAdminId(adminId);
    }

    @GetMapping(value = "/{adminId}/restaurants/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getRestaurantByIdAndAdminId(@PathVariable("id") long id) {
        long adminId = SecurityUtil.authUserId();
        log.info("get restaurants {} for admin {}", id, adminId);
        return restaurantService.getRestaurantByIdAndAdminId(id, adminId);
    }

    @DeleteMapping(value = "/restaurants/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteRestaurantByIdAndByAdminId(@PathVariable("id") long id) {
        long adminId = SecurityUtil.authUserId();
        log.info("delete restaurant {} for admin {}", id, adminId);
        restaurantService.delete(id, adminId);
    }

    @PostMapping(value = "/restaurants", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        long adminId = SecurityUtil.authUserId();
        Restaurant created = restaurantService.create(restaurant, adminId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/admin/restaurant" + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        log.info("create restaurant {} for admin {}", restaurant, adminId);

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/restaurants/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") long id) {
        long adminId = SecurityUtil.authUserId();
        log.info("update restaurant {} for admin {}", restaurant, adminId);
        restaurantService.update(restaurant, adminId);
    }

}
