package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /**
     * Custom Configuration
     * to accept or deny requests
     */
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/account/**", "/balance/**", "/card/**", "/loan/**").authenticated()
                        .requestMatchers("/notice/**", "/contact/**", "/welcome/**", "/register/**").permitAll()
                )
                .formLogin(withDefaultsFormLoginConfigurer())
                .httpBasic(withDefaultsHttpBasicConfigurer());

        return http.build();
    }

    private static Customizer<FormLoginConfigurer<HttpSecurity>> withDefaultsFormLoginConfigurer() {
        return http -> {};
    }

    private static Customizer<HttpBasicConfigurer<HttpSecurity>> withDefaultsHttpBasicConfigurer() {
        return http -> {};
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}