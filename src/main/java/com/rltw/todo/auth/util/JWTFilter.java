package com.rltw.todo.auth.util;

import com.rltw.todo.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    UserService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {


        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        if(!(authorizationHeader == null || authorizationHeader.isEmpty()) && jwtUtil.doesAuthHeaderHaveJWT(authorizationHeader)){
                String token = jwtUtil.extractTokenFromAuthHeader(authorizationHeader);
                long userId = jwtUtil.getUserIdFromToken(token);
                var userDetails =  userDetailsService.findById(userId);
                if(userDetails.isPresent()){
                    SecurityContextHolder.getContext().setAuthentication(
                            new UsernamePasswordAuthenticationToken(userDetails.get(),
                            null,
                            new ArrayList<>())
                    );
                }
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
