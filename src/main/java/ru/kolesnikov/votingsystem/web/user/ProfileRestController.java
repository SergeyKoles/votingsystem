package ru.kolesnikov.votingsystem.web.user;

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
@RequestMapping("/profile")
public class ProfileRestController {

    @Autowired
    private VoteService voteService;

    @PostMapping(value = "/restaurants/{id}/votes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createOrUpdateVote(@RequestBody Vote vote, @PathVariable("id") long restaurantId) {
        long userId = SecurityUtil.authUserId();

        Vote created = voteService.create(vote, restaurantId, userId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/restaurants/{id}/votes" + "/{id}")
                .path("/restaurants/{id}/votes")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/votes/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteVote(@PathVariable("id") long id) {
        long userId = SecurityUtil.authUserId();
        voteService.deleteVoteById(id, userId);
    }
}
