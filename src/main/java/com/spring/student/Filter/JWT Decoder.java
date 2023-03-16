package com.spring.student.Filter;

import com.spring.student.constant.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
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

public class JWTDecoder extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String jwt = request.getHeader(security.HEADER);
        if (jwt !=null) {
    try {
    SecretKey secretKey= Keys.hmacShaKeyFor(security.KEY.getBytes(StandardCharsets.UTF_8));
    Claims claims= Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(jwt)
            .getBody();
    String email = String.valueOf(claims.get("email"));
    String authorities= String.valueOf(claims.get("Role"));
    Authentication authentication= new UsernamePasswordAuthenticationToken(email,null, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
    SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (Exception e){
    throw new BadCredentialsException("invalid token");
        }


        }
        filterChain.doFilter(request,response);
    }



}