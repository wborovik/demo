package com.example.demo.service;

import com.example.demo.model.Vote;
import com.example.demo.repositiry.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VoteServiceImpl implements VoteService {
    VoteRepository voteRepository;

    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }


    @Override
    public List<Vote> getAllVote() {
        return null;
    }

    @Override
    public Vote getVoteById(Long id) {
        return null;
    }

    @Override
    public void createVote(Vote vote) {

    }

    @Override
    public void updateVoteById(Long id, Vote vote) {

    }

    @Override
    public void deleteVoteById(Long id) {

    }
}
