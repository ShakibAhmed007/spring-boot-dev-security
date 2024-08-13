package com.example.springsecurity.login;

import com.example.springsecurity.registration.entity.Customer;
import com.example.springsecurity.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping
    public Customer login(Authentication authentication){
        Customer customer = customerRepository.findByEmail(authentication.getName());
        if(customer != null){
            return customer;
        } else {
            return null;
        }
    }
}
