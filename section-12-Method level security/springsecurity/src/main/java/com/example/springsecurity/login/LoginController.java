package com.example.springsecurity.login;

import com.example.springsecurity.consts.ApplicationConstants;
import com.example.springsecurity.login.model.LoginRequestDTO;
import com.example.springsecurity.login.model.LoginResponseDTO;
import com.example.springsecurity.registration.entity.Customer;
import com.example.springsecurity.repository.CustomerRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.env.Environment;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    CustomerRepository customerRepository;

    private AuthenticationManager authenticationManager;
    private Environment environment;

    @GetMapping
    public Customer login(Authentication authentication){
        Customer customer = customerRepository.findByEmail(authentication.getName());
        if(customer != null){
            return customer;
        } else {
            return null;
        }
    }

    @PostMapping("/apiLogin")
    public ResponseEntity<LoginResponseDTO> apiLogin(@RequestBody LoginRequestDTO loginRequestDTO){
        String jwt = null;
        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(loginRequestDTO.getUserName(), loginRequestDTO.getPassword());
        Authentication authenticationResponse = authenticationManager.authenticate(authentication);
        if(authenticationResponse != null && authenticationResponse.isAuthenticated()){
            if(environment != null){
                String secret = environment.getProperty(ApplicationConstants.JWT_SECRET, ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
                SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
                jwt = Jwts.builder().issuer("SPRING_SECURITY").subject("JWT_TOKEN")
                        .claim("username", authenticationResponse.getName())
                        .claim("authorities", authenticationResponse.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                        .issuedAt(new Date())
                        .expiration(new Date((new Date()).getTime() + 300000000))
                        .signWith(secretKey).compact();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).header(ApplicationConstants.JWT_HEADER, jwt).body(new LoginResponseDTO(HttpStatus.OK.getReasonPhrase(), jwt));
    }
}
