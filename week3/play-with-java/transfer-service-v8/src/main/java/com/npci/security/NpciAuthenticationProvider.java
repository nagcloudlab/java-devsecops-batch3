package com.npci.security;

import com.npci.model.User;
import com.npci.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class NpciAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) {
        NpciAuthenticationToken token = (NpciAuthenticationToken) authentication;
        String username = token.getName();
        String rawPassword = token.getCredentials().toString();

        System.out.println("[PROVIDER] Username: " + username);
        System.out.println("[PROVIDER] Password: " + rawPassword);

        // Fetch user entity
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Check password with encoder
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        // Success: create authenticated token with authorities
        NpciAuthenticationToken authenticatedToken =
                new NpciAuthenticationToken(username, null, user.getRoles());

        return authenticatedToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return NpciAuthenticationToken.class.equals(authentication);
    }
}
