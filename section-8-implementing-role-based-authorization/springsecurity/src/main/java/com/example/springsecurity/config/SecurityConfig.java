package com.example.springsecurity.config;

import com.example.springsecurity.filter.CsrfTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        csrfTokenRequestAttributeHandler.setCsrfRequestAttributeName("_csrf");

        http
                .securityContext(context -> context.requireExplicitSave(false))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .cors(httpSecurityCoresConfigure -> httpSecurityCoresConfigure.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                        .ignoringRequestMatchers("/auth/signup")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .addFilterAfter(new CsrfTokenFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests((requests) -> requests

                        // authority based
                        /*.requestMatchers("/account/**").hasAuthority("VIEWACCOUNT")
                        .requestMatchers("/balance/**").hasAuthority("VIEWBALANCE")
                        .requestMatchers("/card/**").hasAuthority("VIEWCARDS")
                        .requestMatchers("/loan/**").hasAuthority("VIEWLOANS")*/

                        // role based
                        .requestMatchers("/account/**").hasRole("USER")
                        .requestMatchers("/balance/**").hasAnyRole("USER", "ADMIN", "MANAGER")
                        .requestMatchers("/card/**").hasRole("USER")
                        .requestMatchers("/loan/**").hasRole("USER")
                        .requestMatchers( "/login", "/contact/**").authenticated()
                        .requestMatchers("/notice/**", "/welcome/**", "/auth/**").permitAll()
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(withDefaultsHttpBasicConfigurer());
        return http.build();
    }

    private static Customizer<HttpBasicConfigurer<HttpSecurity>> withDefaultsHttpBasicConfigurer() {
        return http -> {};
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
