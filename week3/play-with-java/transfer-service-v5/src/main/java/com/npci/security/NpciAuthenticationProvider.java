package com.npci.security;

import com.npci.model.User;
import com.npci.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class NpciAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private NpciUserDetailsService npciUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) {
        NpciAuthenticationToken token = (NpciAuthenticationToken) authentication;

        String username = token.getName();
        String rawPassword = token.getCredentials().toString();
        String rsaToken = token.getRsaToken();


        // Fetch user entity
        UserDetails user = npciUserDetailsService.loadUserByUsername(username);

        // Check password with encoder
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        // Validate RSA token
        if (rsaToken == null || !rsaToken.equals("121212")) {
            throw new BadCredentialsException("Invalid RSA token");
        }

        // Success: create authenticated token with authorities
        NpciAuthenticationToken authenticatedToken =
                new NpciAuthenticationToken(username, null, user.getAuthorities());

        return authenticatedToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return NpciAuthenticationToken.class.equals(authentication);
    }
}
