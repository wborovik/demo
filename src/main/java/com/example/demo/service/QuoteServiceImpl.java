package com.example.demo.service;

import com.example.demo.model.Quote;
import com.example.demo.model.User;
import com.example.demo.repositiry.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void createQuote(Long userId, Quote quote) {

        User user = userService.getUserById(userId).get();
        quote.setUser(user);
        quoteRepository.save(quote);
    }
}
