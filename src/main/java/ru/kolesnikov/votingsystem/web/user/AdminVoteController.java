package ru.kolesnikov.votingsystem.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.kolesnikov.votingsystem.service.VoteService;
import ru.kolesnikov.votingsystem.web.SecurityUtil;

@RestController
@RequestMapping("/admin/votes")
public class AdminVoteController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService voteService;

    @DeleteMapping(value = "/reset")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAll() {
        long adminId = SecurityUtil.authUserId();
        log.info("reset votes by admin {}", adminId);
        voteService.deleteAll();
    }
}
