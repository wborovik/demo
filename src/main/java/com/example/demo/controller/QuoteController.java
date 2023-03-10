package com.example.demo.controller;

import com.example.demo.model.Quote;
import com.example.demo.service.QuoteService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuoteController {
    private final QuoteService quoteService;
    private final UserService userService;

    @Autowired
    public QuoteController(QuoteService quoteService, UserService userService) {
        this.quoteService = quoteService;
        this.userService = userService;
    }

    @GetMapping("/quotes")
    public ResponseEntity<List<Quote>> getAllQuotes() {
        try {
            List<Quote> quotes = new ArrayList<>(quoteService.getAllQuotes());

            if (quotes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(quotes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/quotes/{userId}")
    public ResponseEntity<List<Quote>> getAllQuotesByUserId(@PathVariable Long userId) {
        try {
            List<Quote> quotes = new ArrayList<>(quoteService.getAllQuotesByUserId(userId));

            if (quotes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(quotes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/quotes/top")
    public ResponseEntity<List<Quote>> getTopQuotes() {
        try {
            List<Quote> quotes = new ArrayList<>(quoteService.getTopQuotes());

            if (quotes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(quotes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/quotes/flop")
    public ResponseEntity<List<Quote>> getFlopQuotes() {
        try {
            List<Quote> quotes = new ArrayList<>(quoteService.getFlopQuotes());

            if (quotes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(quotes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/quote/random")
    public ResponseEntity<Quote> getRandomQuote() {
        Quote quote = quoteService.getRandomQuote();
        if (quote == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(quote, HttpStatus.OK);
    }

    @GetMapping("/quote/{id}")
    public ResponseEntity<Quote> getQuoteById(@PathVariable Long id) {
        Quote quote = quoteService.getQuoteById(id);

        if (quote == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(quote, HttpStatus.OK);
    }

    @PostMapping("/quote/create/{userId}")
    public ResponseEntity<Quote> createQuote(@PathVariable Long userId, @RequestBody Quote quote) {
        if (quote == null || userService.getUserById(userId) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        quoteService.createQuote(userId, quote);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/quote/update/{id}")
    public ResponseEntity<Quote> updateQuoteById(@PathVariable Long id, @RequestBody Quote quote) {
        if (quote == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.quoteService.updateQuoteById(id, quote);

        return new ResponseEntity<>(quote, HttpStatus.OK);
    }

    @DeleteMapping("/quote/delete/{id}")
    public ResponseEntity<HttpStatus> deleteQuoteById(@PathVariable Long id) {
        quoteService.deleteQuoteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}




