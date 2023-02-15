package com.example.demo.service;

import com.example.demo.model.Vote;

import java.util.List;

public interface VoteService {
    List<Vote> getAllVote();

    Vote getVoteById(Long id);

    void createVote(Vote vote);

    void updateVoteById(Long id, Vote vote);

    void deleteVoteById(Long id);
}
