package com.gabinishimwe.springdatajpa.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//@EnableWebSecurity
//public class WebSecurityConfig {
//    private static final String[] WHITE_LIST_URL = {
//            "/hello",
//            "/register"
//    };
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(11);
//    }
//f
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.cors().and().csrf().disable().authorizeHttpRequests().antMatchers(WHITE_LIST_URL).permitAll();
//        return httpSecurity.build();
//    }
//}
