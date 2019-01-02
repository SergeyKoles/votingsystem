package ru.kolesnikov.votingsystem.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.annotation.PostConstruct;

import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.kolesnikov.votingsystem.RestaurantTestData.DODO_ID;
import static ru.kolesnikov.votingsystem.RestaurantTestData.*;
import static ru.kolesnikov.votingsystem.util.RestaurantUtil.*;

class RootControllerTest extends AbstractControllerTest{

    @Test
    public void getAllWithRateAndMenu() throws Exception {
        mockMvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(getToMatcher(getAllWithRateAndDishes(VOTES_WITH_RESTAURANTS, DISHES_WITH_RESTAURANT)))
        ;
    }

    @Test
    public void getRestaurantByIdWithMenuAndRate() throws Exception {
        mockMvc.perform(get("/restaurants/" + DODO_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(getToMatcher(getAllWithRateAndDishes(
                        VOTES_WITH_RESTAURANTS.stream().filter(vote -> vote.getRestaurant().getId()==DODO_ID).collect(Collectors.toList()),
                        DISHES_WITH_RESTAURANT.stream().filter(vote -> vote.getRestaurant().getId()==DODO_ID).collect(Collectors.toList()))))
        ;
    }
}