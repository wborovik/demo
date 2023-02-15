package com.example.demo.controller;

import com.example.demo.model.Quote;
import com.example.demo.model.User;
import com.example.demo.repositiry.QuoteRepository;
import com.example.demo.repositiry.UserRepository;
import com.example.demo.service.QuoteService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class QuoteController {
    private final QuoteService quoteService;
    private final UserService userService;

    @Autowired
    public QuoteController(QuoteService quoteService, UserService userService) {
        this.quoteService = quoteService;
        this.userService = userService;
    }

    @GetMapping("/getAllQuotes")
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

    @GetMapping("/getQuoteById/{id}")
    public ResponseEntity<Quote> getQuoteById(@PathVariable Long id) {
        Quote quote = quoteService.getQuoteById(id);

        if (quote == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(quote, HttpStatus.OK);
    }

    @PostMapping("/{id}/createQuote")
    public ResponseEntity<Quote> createQuote(@PathVariable Long id, @RequestBody Quote quote) {
        if (quote == null || userService.getUserById(id) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        quoteService.createQuote(id, quote);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/updateQuoteById/{id}")
    public ResponseEntity<Quote> updateQuoteById(@PathVariable Long id, @RequestBody Quote quote) {
        if (quote == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.quoteService.updateQuoteById(id, quote);

        return new ResponseEntity<>(quote, HttpStatus.OK);
    }

    @DeleteMapping("/deleteQuoteById/{id}")
    public ResponseEntity<HttpStatus> deleteQuoteById(@PathVariable Long id) {
        quoteService.deleteQuoteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}




