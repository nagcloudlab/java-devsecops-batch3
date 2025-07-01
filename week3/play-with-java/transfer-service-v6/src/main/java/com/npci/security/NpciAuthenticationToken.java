package com.npci.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class NpciAuthenticationToken extends AbstractAuthenticationToken {

    private final String principal;
    private final String credentials;
    private String rsaToken;

    // ✅ Constructor used before authentication (e.g., in filter)
    public NpciAuthenticationToken(String principal, String credentials, String rsaToken) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.rsaToken = rsaToken;
        setAuthenticated(false);
    }

    // ✅ Constructor used after authentication with roles
    public NpciAuthenticationToken(String principal, String credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public String getRsaToken() {
        return rsaToken;
    }

    public void setRsaToken(String rsaToken) {
        this.rsaToken = rsaToken;
    }
}
