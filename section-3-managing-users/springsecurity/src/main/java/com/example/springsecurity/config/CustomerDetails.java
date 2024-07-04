package com.example.springsecurity.config;

import com.example.springsecurity.model.Customer;
import com.example.springsecurity.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerDetails implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName, password = null;
        List<GrantedAuthority> authorityList = null;
        Customer customer = customerRepository.findByEmail(username);

        if(customer == null){
            throw new UsernameNotFoundException("Customer Details not found for the email: "+ username);
        } else {
            userName = customer.getEmail();
            password = customer.getPassword();
            authorityList = new ArrayList<>();
            authorityList.add(new SimpleGrantedAuthority(customer.getRole()));
        }
        return new User(userName, password, authorityList);
    }
}
