package com.jproject.my_cars.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(
//                (authz) ->
//                        authz
//                                .requestMatchers("/board/write/**").hasAnyRole("MEMBER","DEALER")
//                                .requestMatchers("/board/modify/**").hasAnyRole("MEMBER","DEALER")
//                                .requestMatchers("/cars/posts").hasRole("DEALER")
//                                .requestMatchers("/cars/remove/**").hasRole("DEALER")
//                                .requestMatchers("/cars/modify/**").hasRole("DEALER")
//                                .requestMatchers("/cars/sale/**").hasRole("DEALER")
//                                .requestMatchers("/dealer/dealerPage").hasRole("DEALER")
//                                .requestMatchers("/member/mypage").hasRole("MEMBER")
//                                .requestMatchers("/member/removeLikes/**").hasRole("MEMBER")
//                                .requestMatchers("/reply/writeAction/**").hasAnyRole("MEMBER","DEALER")
//                                .requestMatchers("/reply/remove/**").hasAnyRole("MEMBER","DEALER")
//                                .requestMatchers("/reply/modify/**").hasAnyRole("MEMBER","DEALER")
//                                .anyRequest().permitAll()
//        )
//                .formLogin(
//                        (form) -> form
//                                .loginPage("/member/login")
//                                .loginPage("/dealer/login")
//                                .permitAll()
//                );
//        return http.build();
//    }
}
