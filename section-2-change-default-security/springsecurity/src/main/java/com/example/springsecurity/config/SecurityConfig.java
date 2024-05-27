package com.example.springsecurity.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /**
     * Custom Config to accept or deny requests
     */
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/account/**", "/balance/**", "/card/**", "/loan/**").authenticated()
                        .requestMatchers("/notice/**", "/contact/**", "/welcome/**").permitAll()
                )
                .formLogin(withDefaultsFormLoginConfigurer())
                .httpBasic(withDefaultsHttpBasicConfigurer());

        return http.build();
    }

    /**
     * Config to deny All Requests
     */
//    @Bean
//    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((requests) -> requests.anyRequest().denyAll())
//                .formLogin(withDefaultsFormLoginConfigurer())
//                .httpBasic(withDefaultsHttpBasicConfigurer());
//
//        return http.build();
//    }

    /**
     * Config to accept All Requests
     */
//    @Bean
//    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((requests) -> requests.anyRequest().permitAll())
//                .formLogin(withDefaultsFormLoginConfigurer())
//                .httpBasic(withDefaultsHttpBasicConfigurer());
//
//        return http.build();
//    }

    private static Customizer<FormLoginConfigurer<HttpSecurity>> withDefaultsFormLoginConfigurer() {
        return http -> {};
    }

    private static Customizer<HttpBasicConfigurer<HttpSecurity>> withDefaultsHttpBasicConfigurer() {
        return http -> {};
    }
}
