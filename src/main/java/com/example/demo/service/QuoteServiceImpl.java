package com.example.demo.service;

import com.example.demo.model.Quote;
import com.example.demo.model.User;
import com.example.demo.repositiry.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuoteServiceImpl implements QuoteService {
    QuoteRepository quoteRepository;
    UserService userService;

    @Autowired
    public QuoteServiceImpl(QuoteRepository quoteRepository, UserService userService) {
        this.quoteRepository = quoteRepository;
        this.userService = userService;
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
    public void createQuote(Long id, Quote quote) {

        User user = userService.getUserById(id);
        if (user != null) {
            quote.setUser(user);

            quoteRepository.save(quote);
        }
    }

    @Override
    public void updateQuoteById(Long id, Quote quote) {
        Quote quoteUpdate = getQuoteById(id);
        quoteUpdate.setQuote(quote.getQuote());

        quoteRepository.save(quoteUpdate);
    }

    @Override
    public void deleteQuoteById(Long id) {
        quoteRepository.deleteById(id);
    }
}
