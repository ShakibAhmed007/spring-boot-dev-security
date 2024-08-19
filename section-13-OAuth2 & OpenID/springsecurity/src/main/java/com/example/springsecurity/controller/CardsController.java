package com.example.springsecurity.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardsController {

    @GetMapping("/cardsDetails")
    public String getCardDetails(){
        return "My Cards details";
    }

}
