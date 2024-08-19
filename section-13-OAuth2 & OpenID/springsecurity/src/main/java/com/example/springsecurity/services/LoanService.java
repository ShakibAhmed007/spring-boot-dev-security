package com.example.springsecurity.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    @PreAuthorize("hasRole('USER')")
    public String getLoanDetails(){
        return "Loan Details !!!";
    }
}
