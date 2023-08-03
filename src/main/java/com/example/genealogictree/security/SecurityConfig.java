package com.example.genealogictree.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwk;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests((authorize)-> authorize
                .mvcMatchers(HttpMethod.GET,"genealogictree/*").hasAuthority("full:genealogictree")
                .mvcMatchers(HttpMethod.POST,"genealogictree/*").hasAuthority("full:genealogictree")
                .mvcMatchers(HttpMethod.PUT,"genealogictree/*").hasAuthority("full:genealogictree")
                .mvcMatchers(HttpMethod.DELETE,"genealogictree/*").hasAuthority("full:genealogictree")
                .anyRequest().authenticated()

        ).oauth2ResourceServer().jwt();


        return http.build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(this.jwk).build();
    }
}
