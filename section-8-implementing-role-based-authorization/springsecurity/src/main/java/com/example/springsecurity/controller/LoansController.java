package com.example.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/loan")
public class LoansController {

    @GetMapping("/loanDetails")
    public String getLoanDetails(){
        return "My Loan details";
    }
}
