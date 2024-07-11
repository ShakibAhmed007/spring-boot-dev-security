package com.example.springsecurity.registration.controller;

import com.example.springsecurity.registration.dto.CustomerDTO;
import com.example.springsecurity.exception.ErrorResponse;
import com.example.springsecurity.registration.entity.Customer;
import com.example.springsecurity.registration.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class SignUpController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@Valid @RequestBody CustomerDTO customerDTO){
        try{
            Customer savedCustomer = customerService.saveCustomer(customerDTO);
            return ResponseEntity.ok(savedCustomer);
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(HttpStatus.CONFLICT.value(), "Email already exists"));
        }
    }
}
