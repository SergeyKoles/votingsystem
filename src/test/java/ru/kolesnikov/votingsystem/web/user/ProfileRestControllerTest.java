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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.kolesnikov.votingsystem.UserTestData.USER_A;
import static ru.kolesnikov.votingsystem.VoteTestData.*;
import static ru.kolesnikov.votingsystem.VoteTestData.assertMatch;
import static ru.kolesnikov.votingsystem.web.TestUtil.readFromJsonResultActions;

class ProfileRestControllerTest extends AbstractControllerTest {

    @Autowired
    private VoteService voteService;

    @Test
    public void createOrUpdateVote() throws Exception {
        Vote created = new Vote();

        ResultActions action = mockMvc.perform(post("/profile/restaurants/100033/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)));

        Vote returned = readFromJsonResultActions(action, Vote.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
    }
}