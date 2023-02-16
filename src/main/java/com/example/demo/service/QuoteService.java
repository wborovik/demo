package com.example.demo.service;

import com.example.demo.model.Quote;

import java.util.List;

public interface QuoteService {
    List<Quote> getAllQuotes();

    Quote getQuoteById(Long id);

    List<Quote> getTopQuotes();

    List<Quote> getFlopQuotes();

    Quote getRandomQuote();

    void createQuote(Long id, Quote quote);

    void updateQuoteById(Long id, Quote quote);

    void deleteQuoteById(Long id);
}
