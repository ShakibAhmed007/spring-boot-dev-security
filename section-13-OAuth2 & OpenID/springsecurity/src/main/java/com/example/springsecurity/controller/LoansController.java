package com.example.springsecurity.controller;

import com.example.springsecurity.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/loan")
public class LoansController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/loanDetails")
    public ResponseEntity<String> getLoanDetails(){
        return ResponseEntity.ok(loanService.getLoanDetails());
    }

    @GetMapping()
    public ResponseEntity<String> getLoanDetails(@RequestParam("id") Long id){
        return ResponseEntity.ok(loanService.getLoanDetails());
    }
}
