package com.spring.student.Filter;

import com.spring.student.constant.security;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class JwtTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            SecretKey secretKey = Keys.hmacShaKeyFor(security.KEY.getBytes(StandardCharsets.UTF_8));
            String jwt= Jwts.builder().setSubject("JWT")
                    .claim("email",authentication.getName())
                    .claim("authority",authorities(authentication.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime()+30000))
                    .signWith(secretKey).compact();
            response.setHeader(security.HEADER,jwt);
        }
        filterChain.doFilter(request,response);
    }

    private String authorities(Collection<? extends GrantedAuthority> collections) {
        Set<String> auth = new HashSet<>();
        for (GrantedAuthority authority : collections) {
            auth.add(authority.getAuthority());
        }
        return String.join(",", auth);
    }

}