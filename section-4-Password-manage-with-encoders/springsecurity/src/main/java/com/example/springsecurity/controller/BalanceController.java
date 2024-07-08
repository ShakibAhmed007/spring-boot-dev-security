package com.example.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/balance")
public class BalanceController {

    @GetMapping("/balanceDetails")
    public String getBalanceDetails(){
        return "My Balance details";
    }
}
