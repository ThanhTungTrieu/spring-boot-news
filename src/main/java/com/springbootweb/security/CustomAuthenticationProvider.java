package com.springbootweb.security;

import com.springbootweb.dto.UserDTO;
import com.springbootweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//TODO: Roles in entity to DTO does not match with authorities in Security
//TODO: idk what is going on. Authenticated successfully but cannot have authorization
//TODO: issue on role cannot match
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private IUserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDTO user = userService.findByUsernameAndPassword(username, password);
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role: user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role));
            System.out.println(role);
        }
//        authorities.add(new SimpleGrantedAuthority("USER"));

        return new UsernamePasswordAuthenticationToken(username, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}