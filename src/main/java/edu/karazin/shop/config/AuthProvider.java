package edu.karazin.shop.config;

import edu.karazin.shop.model.Role;
import edu.karazin.shop.model.User;
import edu.karazin.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AuthProvider implements AuthenticationProvider {

    private final UserService userService;

    @Autowired
    public AuthProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = userService.getUser((String) authentication.getPrincipal());
        if (isValidCredentials(authentication, user)) {
            return createAuthentication(authentication, user.getRole());
        } else {
            return null;
        }
    }

    public UsernamePasswordAuthenticationToken createAuthentication(Authentication authentication, Role role) {
        return new UsernamePasswordAuthenticationToken(
                authentication.getPrincipal(),
                authentication.getCredentials(),
                Arrays.asList(new SimpleGrantedAuthority(role.toString())));
    }

    private boolean isValidCredentials(Authentication authentication, User user) {
        return user != null &&  user.getPassword().equals(authentication.getCredentials());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
