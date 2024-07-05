package com.example.springsecurity.controller;

import com.example.springsecurity.dto.CustomerDTO;
import com.example.springsecurity.exception.ErrorResponse;
import com.example.springsecurity.model.Customer;
import com.example.springsecurity.services.CustomerService;
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
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<Object> saveCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        try{
            Customer savedCustomer = customerService.saveCustomer(customerDTO);
            return ResponseEntity.ok(savedCustomer);
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(HttpStatus.CONFLICT.value(), "Email already exists"));
        }
    }
}
