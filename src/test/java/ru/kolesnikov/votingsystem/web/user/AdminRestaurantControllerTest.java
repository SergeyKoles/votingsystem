package ru.kolesnikov.votingsystem.web.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import ru.kolesnikov.votingsystem.model.Restaurant;
import ru.kolesnikov.votingsystem.service.RestaurantService;
import ru.kolesnikov.votingsystem.web.AbstractControllerTest;
import ru.kolesnikov.votingsystem.web.json.JsonUtil;

import javax.annotation.PostConstruct;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.kolesnikov.votingsystem.RestaurantTestData.*;
import static ru.kolesnikov.votingsystem.RestaurantTestData.KFC;
import static ru.kolesnikov.votingsystem.UserTestData.*;
import static ru.kolesnikov.votingsystem.model.AbstractBaseEntity.START_SEQ;
import static ru.kolesnikov.votingsystem.web.TestUtil.readFromJsonResultActions;


class AdminRestaurantControllerTest extends AbstractControllerTest {

    @Autowired
    private RestaurantService restaurantService;

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get("/admin/restaurants"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(getToMatcherEntity(DODO, TEREMOK, MD, OLIS, LESTER, KFC));
    }

    @Test
    public void getRestaurantsByAdminId() throws Exception {
        mockMvc.perform(get("/admin/" + ADMIN_A_ID + "/restaurants"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(getToMatcherEntity(DODO, OLIS));
    }

    @Test
    public void getRestaurantByIdAndAdminId() throws Exception {
        mockMvc.perform(get("/admin/" + ADMIN_A_ID + "/restaurants/" + DODO_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(getToMatcherEntity(DODO));
    }

    @Test
    void deleteRestaurantByIdAndByAdminId() throws Exception {
        mockMvc.perform(delete("/admin/restaurants/" + DODO_ID))
                .andExpect(status().isNoContent());
        assertMatch(restaurantService.getAllByAdminId(ADMIN_B_ID), TEREMOK, MD, LESTER, KFC);
    }

    @Test
    void createRestaurant() throws Exception {
        Restaurant created = new Restaurant("Drova");

        ResultActions action = mockMvc.perform(post("/admin/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)));

        Restaurant returned = readFromJsonResultActions(action, Restaurant.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(restaurantService.getAll(), DODO, TEREMOK, MD, OLIS, LESTER, KFC, created);
    }

    @Test
    void update() throws Exception {
        Restaurant updated = new Restaurant(DODO_ID, "Super Dodo");
        updated.setAdmin(ADMIN_A);

        mockMvc.perform(put("/admin/restaurants/" + DODO_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        assertMatch(restaurantService.getRestaurantByIdAndAdminId(DODO_ID, ADMIN_A_ID), updated);
    }
}