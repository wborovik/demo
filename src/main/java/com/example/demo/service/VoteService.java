package com.example.demo.service;

import com.example.demo.model.Vote;

public interface VoteService {
    Vote getVoteById(Long id);

    void createVote(Vote vote);

    void plusVote(Vote vote);

    void minusVote(Vote vote);
}
