package com.npci.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class NpciSecurityConfiguration {

    private NpciAuthenticationProvider npciAuthenticationProvider;

    @Autowired
    public NpciSecurityConfiguration(NpciAuthenticationProvider npciAuthenticationProvider) {
        this.npciAuthenticationProvider = npciAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(npciAuthenticationProvider)
                .build();
    }


    @Bean
    public NpciAuthenticationFilter npciAuthenticationFilter(AuthenticationManager authenticationManager) {
        NpciAuthenticationFilter npciAuthenticationFilter = new NpciAuthenticationFilter(authenticationManager);
        npciAuthenticationFilter.setFilterProcessesUrl("/authenticate");
        return npciAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/transfer-form", "/transfer", "/dashboard").authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/authenticate")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
//                .exceptionHandling(ex -> ex
//                        .accessDeniedPage("/error/403") // 👈 Authorization (403) error path
//                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                .addFilterBefore(npciAuthenticationFilter(authenticationManager(http)),
                        UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
