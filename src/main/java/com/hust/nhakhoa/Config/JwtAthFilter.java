package com.hust.nhakhoa.Config;

import com.hust.nhakhoa.Exceptions.UnauthenticatedRequestHandler;
import com.hust.nhakhoa.Service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAthFilter extends OncePerRequestFilter{


    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UnauthenticatedRequestHandler unauthenticatedRequestHandler;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;

        //kiem tra jwt co ton tai hay k
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            jwtToken = authHeader.substring(7);
            userEmail = jwtService.extractUsername(jwtToken);
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
                if (jwtService.isTokenValid(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            unauthenticatedRequestHandler.commence(request, response, new CredentialsExpiredException("Expired JWT Token"));
        } catch (MalformedJwtException e) {
            unauthenticatedRequestHandler.commence(request, response, new BadCredentialsException("Invalid JWT Token"));
        } catch (JwtException e) {
            unauthenticatedRequestHandler.commence(request, response, new BadCredentialsException("JWT Token error"));
        } catch (UsernameNotFoundException e) {
            unauthenticatedRequestHandler.commence(request, response, new UsernameNotFoundException("Invalid token - user not found"));
        }

    }
}
