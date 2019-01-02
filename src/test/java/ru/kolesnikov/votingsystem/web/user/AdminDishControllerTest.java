package ru.kolesnikov.votingsystem.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.kolesnikov.votingsystem.model.Dish;
import ru.kolesnikov.votingsystem.service.DishService;
import ru.kolesnikov.votingsystem.web.AbstractControllerTest;
import ru.kolesnikov.votingsystem.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.kolesnikov.votingsystem.DishTestData.*;
import static ru.kolesnikov.votingsystem.RestaurantTestData.*;
import static ru.kolesnikov.votingsystem.web.TestUtil.*;

class AdminDishControllerTest extends AbstractControllerTest {

    @Autowired
    private DishService dishService;

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(delete("/admin/restaurants/" + DODO_ID + "/dishes/" + MORGARITA_ID))
                .andExpect(status().isNoContent());
        assertMatch(dishService.getAllByRestaurantId(DODO_ID), PEPERONI, CEZAR);
    }

    @Test
    void update() throws Exception {
        Dish updated = new Dish(MORGARITA_ID, "Double Morgarita", 400L);
        updated.setRestaurant(DODO);

        mockMvc.perform(put("/admin/restaurants/" + DODO_ID + "/dishes/" + MORGARITA_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        assertMatch(dishService.getAllByRestaurantId(DODO_ID), PEPERONI, CEZAR, updated);
    }

    @Test
    void createDish() throws Exception {
        Dish created = new Dish("Borch", 111L);

        ResultActions action = mockMvc.perform(post("/admin/restaurants/" + DODO_ID + "/dishes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)));

        Dish returned = readFromJsonResultActions(action, Dish.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(dishService.getAllByRestaurantId(DODO_ID), MORGARITA, PEPERONI, CEZAR, created);
    }
}