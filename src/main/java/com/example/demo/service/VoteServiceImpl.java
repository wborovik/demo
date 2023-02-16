package com.example.demo.service;

import com.example.demo.model.Vote;
import com.example.demo.repositiry.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VoteServiceImpl implements VoteService {
    private final VoteRepository voteRepository;

    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Override
    public Vote getVoteById(Long id) {
        return voteRepository.findById(id).orElse(null);
    }

    @Override
    public void createVote(Vote vote) {
        voteRepository.save(vote);
    }

    @Override
    public void plusVote(Vote vote) {
        vote.setVote(vote.getVote() + 1);
        voteRepository.save(vote);
    }

    @Override
    public void minusVote(Vote vote) {
        vote.setVote(vote.getVote() - 1);
        voteRepository.save(vote);
    }
}
