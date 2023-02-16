package com.example.demo.service;

import com.example.demo.model.Quote;
import com.example.demo.model.User;
import com.example.demo.model.Vote;
import com.example.demo.repositiry.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository quoteRepository;
    private final UserService userService;
    private final VoteService voteService;

    @Autowired
    public QuoteServiceImpl(QuoteRepository quoteRepository, UserService userService, VoteService voteService) {
        this.quoteRepository = quoteRepository;
        this.userService = userService;
        this.voteService = voteService;
    }

    @Override
    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    @Override
    public Quote getQuoteById(Long id) {
        return quoteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Quote> getTopQuotes() {
        return quoteRepository.findAll().stream()
                .sorted((o1, o2) -> o2.getVote().getVote() - o1.getVote().getVote()).limit(10).toList();
    }

    @Override
    public List<Quote> getFlopQuotes() {
        return quoteRepository.findAll().stream()
                .sorted(Comparator.comparingInt(o -> o.getVote().getVote())).limit(10).toList();
    }

    @Override
    public Quote getRandomQuote() {
        Optional<Quote> quoteOptional = quoteRepository.findAll().stream().findAny();
        return quoteOptional.orElse(null);
    }

    @Override
    public void createQuote(Long id, Quote quote) {
        User user = userService.getUserById(id);
        Vote vote = new Vote();
        vote.setVote(0);
        if (user != null) {
            quote.setUser(user);
            voteService.createVote(vote);
            Quote createQuote = quoteRepository.save(quote);
            createQuote.setVote(vote);
        }
    }

    @Override
    public void updateQuoteById(Long id, Quote quote) {
        Quote quoteUpdate = getQuoteById(id);
        quoteUpdate.setQuote(quote.getQuote());
        quoteUpdate.setDateCreateOrUpdate(LocalDate.now());

        quoteRepository.save(quoteUpdate);
    }

    @Override
    public void deleteQuoteById(Long id) {
        quoteRepository.deleteById(id);
    }
}
