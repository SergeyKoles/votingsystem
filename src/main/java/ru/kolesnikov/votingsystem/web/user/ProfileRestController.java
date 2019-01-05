package ru.kolesnikov.votingsystem.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.kolesnikov.votingsystem.model.Vote;
import ru.kolesnikov.votingsystem.service.VoteService;
import ru.kolesnikov.votingsystem.web.SecurityUtil;

import java.net.URI;


@RestController
@RequestMapping(value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService voteService;

    @PostMapping(value = "/restaurants/{id}/votes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createOrUpdateVote(@PathVariable("id") long restaurantId) {
        long userId = SecurityUtil.authUserId();

        Vote created = voteService.create(restaurantId, userId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/restaurants/{id}/votes" + "/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        log.info("create or update vote for restaurant {} by user {}", restaurantId, userId);
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/votes")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteVote() {
        long userId = SecurityUtil.authUserId();
        log.info("delete vote by user {}", userId);
        voteService.deleteVoteByUserId(userId);
    }
}
