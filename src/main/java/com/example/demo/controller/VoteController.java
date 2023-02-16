package com.example.demo.controller;

import com.example.demo.model.Vote;
import com.example.demo.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VoteController {
    private final VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PutMapping("/vote/plus/{id}")
    public ResponseEntity<Vote> plusVote(@PathVariable Long id) {
        Vote vote = voteService.getVoteById(id);
        if (vote == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.voteService.plusVote(vote);

        return new ResponseEntity<>(vote, HttpStatus.OK);
    }

    @PutMapping("/vote/minus/{id}")
    public ResponseEntity<Vote> minusVote(@PathVariable Long id) {
        Vote vote = voteService.getVoteById(id);
        if (vote == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.voteService.minusVote(vote);

        return new ResponseEntity<>(vote, HttpStatus.OK);
    }
}
