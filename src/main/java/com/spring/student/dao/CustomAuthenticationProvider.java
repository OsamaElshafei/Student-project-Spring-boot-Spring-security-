package com.spring.student.dao;



import com.spring.student.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private LoginRepo loginRepo;
    private PasswordEncoder passwordEncoder;
@Autowired
    public CustomAuthenticationProvider(LoginRepo subscriberRepo, PasswordEncoder passwordEncoder) {
        this.loginRepo = subscriberRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<Login> loginList = loginRepo.findByEmail(name);
        if (loginList.isEmpty()) {
            throw new BadCredentialsException("valiad user name");
        } else {
            if (passwordEncoder.matches(password, loginList.get(0).getPassword())) {
               List<GrantedAuthority> authorityList = new ArrayList<>();
               authorityList.add(new SimpleGrantedAuthority(loginList.get(0).getRole()));
                return new UsernamePasswordAuthenticationToken(name, password,authorityList);
            } else {
                throw new BadCredentialsException("Invalid Password");
            }
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
     return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
