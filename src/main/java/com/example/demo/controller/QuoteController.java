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

@RestController
public class QuoteController {
    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;
    private final QuoteService quoteService;
    private final UserService userService;

    @Autowired
    public QuoteController(QuoteRepository quoteRepository, UserRepository userRepository, QuoteService quoteService,
                           UserService userService) {
        this.quoteRepository = quoteRepository;
        this.userRepository = userRepository;
        this.quoteService = quoteService;
        this.userService = userService;
    }

    @GetMapping("/getAllQuotes")
    public ResponseEntity<List<Quote>> getAllQuotes() {
        try {
            List<Quote> quotes = new ArrayList<>(quoteRepository.findAll());

            if (quotes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(quotes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getUserById/{userId}/createQuote")
    public ResponseEntity<Quote> createQuote(@PathVariable Long userId, @RequestBody Quote quoteRequest) {
        if (userService.getUserById(userId).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        quoteService.createQuote(userId, quoteRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}




