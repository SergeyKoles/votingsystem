package ru.kolesnikov.votingsystem.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.kolesnikov.votingsystem.model.Vote;
import ru.kolesnikov.votingsystem.service.VoteService;
import ru.kolesnikov.votingsystem.web.AbstractControllerTest;
import ru.kolesnikov.votingsystem.web.json.JsonUtil;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.kolesnikov.votingsystem.RestaurantTestData.*;
import static ru.kolesnikov.votingsystem.UserTestData.*;
import static ru.kolesnikov.votingsystem.VoteTestData.*;
import static ru.kolesnikov.votingsystem.VoteTestData.assertMatch;
import static ru.kolesnikov.votingsystem.web.TestUtil.readFromJsonResultActions;
import static ru.kolesnikov.votingsystem.web.TestUtil.userHttpBasic;

class ProfileRestControllerTest extends AbstractControllerTest {

    @Autowired
    private VoteService voteService;

    @Test
    public void createVote() throws Exception {
        Vote created = new Vote();

        ResultActions action = mockMvc.perform(post("/profile/restaurants/" + DODO_ID + "/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN_A))
                .content(JsonUtil.writeValue(created)))
                .andExpect(status().isCreated());

        Vote returned = readFromJsonResultActions(action, Vote.class);
        created.setId(returned.getId());

        assertMatch(voteService.countAllByRestaurantId(DODO_ID), 4);
        assertMatch(returned, created);
    }

    @Test
    public void updateVote() throws Exception {
        assertMatch(voteService.countAllByRestaurantId(DODO_ID), 3);
        assertMatch(voteService.countAllByRestaurantId(TEREMOK_ID), 2);
        Vote updated = voteService.getByUserId(USER_A_ID);

        mockMvc.perform(post("/profile/restaurants/" + TEREMOK_ID + "/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER_A))
                .content(JsonUtil.writeValue(updated)));

        assertMatch(voteService.countAllByRestaurantId(TEREMOK_ID), 3);
        assertMatch(voteService.countAllByRestaurantId(DODO_ID), 2);
        updated.setRestaurant(TEREMOK);
        assertMatch(voteService.getByUserId(USER_A_ID), updated);
    }

    @Test
    public void deleteVote() throws Exception {
        assertMatch(voteService.countAllByRestaurantId(DODO_ID), 3);
        mockMvc.perform(delete("/profile/votes")
                .with(userHttpBasic(USER_A)))
                .andExpect(status().isNoContent());

        assertMatch(voteService.countAllByRestaurantId(DODO_ID), 2);
    }
}