package com.example.demo.service;

import com.example.demo.model.Quote;

public interface QuoteService {
    void createQuote(Long userId, Quote quote);
}
