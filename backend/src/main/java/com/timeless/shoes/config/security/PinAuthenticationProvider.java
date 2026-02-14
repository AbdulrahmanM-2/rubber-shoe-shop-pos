package com.timeless.shoes.security;

import com.timeless.shoes.users.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class PinAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null; // stub
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
