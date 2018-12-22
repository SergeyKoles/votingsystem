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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.kolesnikov.votingsystem.RestaurantTestData.DODO_ID;

@SpringJUnitWebConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-mvc.xml",
        "classpath:spring/spring-db.xml"
})
@Transactional
class RootControllerTest {

    private static final CharacterEncodingFilter CHARACTER_ENCODING_FILTER = new CharacterEncodingFilter();

    static {
        CHARACTER_ENCODING_FILTER.setEncoding("UTF-8");
        CHARACTER_ENCODING_FILTER.setForceEncoding(true);
    }

    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @PostConstruct
    private void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(CHARACTER_ENCODING_FILTER)
                .build();
    }

    @BeforeEach
    void setUp() {
//        cacheManager.getCache("restaurants").clear();
//        if (jpaUtil != null) {
//            jpaUtil.clear2ndLevelHibernateCache();
//        }
    }

    @Test
    public void getAllWithRateAndMenu() throws Exception {
        mockMvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(getToMatcher())
        ;
    }

    @Test
    public void getRestaurantByIdWithMenuAndRate() throws Exception {
        mockMvc.perform(get("/restaurant/" + DODO_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

//    public static void main(String[] args) {
//        List<Dish> dishes = Arrays.asList(
//                new Dish(MORGARITA, DODO),
//                new Dish(PEPERONI, DODO),
//                new Dish(CEZAR, DODO),
//                new Dish(PLAIN, TEREMOK),
//                new Dish(CHICKEN, TEREMOK),
//                new Dish(HAMBURGER, TEREMOK),
//                new Dish(EGGS, MD),
//                new Dish(PORK, MD),
//                new Dish(PEPSI, MD),
//                new Dish(COCA_COLA, OLIS),
//                new Dish(FANTA, OLIS),
//                new Dish(TEA, OLIS),
//                new Dish(COFFEE, LESTER),
//                new Dish(JUICE, LESTER),
//                new Dish(BEER, LESTER),
//                new Dish(MOCHA, KFC),
//                new Dish(BEFF, KFC),
//                new Dish(ICE_CREAM, KFC));
//
//        List<Vote> votes = Arrays.asList(
//                new Vote(VOTE_USER_A, DODO),
//                new Vote(VOTE_USER_B, DODO),
//                new Vote(VOTE_USER_C, DODO),
//                new Vote(VOTE_USER_D, TEREMOK),
//                new Vote(VOTE_USER_E, LESTER),
//                new Vote(VOTE_USER_F, LESTER),
//                new Vote(VOTE_USER_G, KFC),
//                new Vote(VOTE_ADMIN_A, DODO));
//
//
////        getWithRateAndDishes(votes, dishes);
//
////        for (RestaurantTo to : getWithRateAndDishes(votes, dishes)){
////            System.out.println(to.toString());
////        }
//        getWithRateAndDishes(votes, dishes).stream().forEach(r -> System.out.println(r));
//    }
}