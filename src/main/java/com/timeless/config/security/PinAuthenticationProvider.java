package com.timeless.shoes.security;

import com.timeless.shoes.model.User;
import com.timeless.shoes.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PinAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) {

        String phone = authentication.getPrincipal().toString();
        String pin = authentication.getCredentials().toString();

        User user = userService.getActiveUserByPhone(phone);

        if (!userService.verifyPin(user, pin)) {
            throw new BadCredentialsException("Invalid PIN");
        }

        return new PinAuthenticationToken(
                user,
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PinAuthenticationToken.class.isAssignableFrom(authentication);
    }
  }
