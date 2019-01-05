package ru.kolesnikov.votingsystem.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.kolesnikov.votingsystem.service.VoteService;

@RestController
@RequestMapping("/admin/votes")
public class AdminVoteController {

    @Autowired
    private VoteService voteService;

    @DeleteMapping(value = "/reset")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAll(){
        voteService.deleteAll();
    }
}
