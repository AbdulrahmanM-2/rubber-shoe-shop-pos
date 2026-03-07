package com.timeless.shoes.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class PinAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;
    private final Object credentials;

    public PinAuthenticationToken(String phone, String pin) {
        super(null);
        this.principal = phone;
        this.credentials = pin;
        setAuthenticated(false);
    }

    public PinAuthenticationToken(Object principal, List<SimpleGrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = null;
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
}
