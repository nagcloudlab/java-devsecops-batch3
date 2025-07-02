package com.npci.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class NpciSecurityConfiguration {

    private NpciAuthenticationProvider npciAuthenticationProvider;
    private final JwtAuthenticationFilter jwtFilter;

    @Autowired
    public NpciSecurityConfiguration(
            NpciAuthenticationProvider npciAuthenticationProvider,
            JwtAuthenticationFilter jwtFilter
            ) {
        this.npciAuthenticationProvider = npciAuthenticationProvider;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(npciAuthenticationProvider)
                .build();
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST,"/authenticate").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/transfer").authenticated()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/error/403") // 👈 Authorization (403) error path
                )
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}
