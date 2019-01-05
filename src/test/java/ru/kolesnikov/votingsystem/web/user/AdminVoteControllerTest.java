package ru.kolesnikov.votingsystem.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kolesnikov.votingsystem.service.VoteService;
import ru.kolesnikov.votingsystem.web.AbstractControllerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.kolesnikov.votingsystem.RestaurantTestData.*;
import static ru.kolesnikov.votingsystem.VoteTestData.*;
import static ru.kolesnikov.votingsystem.UserTestData.*;
import static ru.kolesnikov.votingsystem.web.TestUtil.userHttpBasic;

class AdminVoteControllerTest extends AbstractControllerTest {

    @Autowired
    private VoteService voteService;

    @Test
    void deleteAll() throws Exception {
        mockMvc.perform(delete("/admin/votes/reset")
                .with(userHttpBasic(ADMIN_A)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(voteService.countAllByRestaurantId(DODO_ID), 0L);
        assertMatch(voteService.countAllByRestaurantId(TEREMOK_ID), 0L);
    }
}