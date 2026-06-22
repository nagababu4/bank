package com.BankSystem.ApiGatway.security;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import io.jsonwebtoken.Claims;
import java.util.Collection;
import java.util.List;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements WebFilter {

    private final JwtUtil jwtUtil;

    public void validate(ServerWebExchange exchange) {

        String authHeader =
                exchange.getRequest()
                        .getHeaders()
                        .getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null ||
                !authHeader.startsWith("Bearer ")) {

            throw new RuntimeException("Missing Token");
        }

        String token =
                authHeader.substring(7);

        jwtUtil.validateToken(token);
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        try {
            
            String path = exchange.getRequest().getPath().toString();
            if (path.startsWith("/auth/register") || path.startsWith("/auth/login")) {
                return chain.filter(exchange);
            }
            
          
            validate(exchange);
            
            String authHeader = exchange.getRequest()
                    .getHeaders()
                    .getFirst(HttpHeaders.AUTHORIZATION);
            String token = authHeader.substring(7);
            
            Claims claims = jwtUtil.getClaims(token);
            
            
            UsernamePasswordAuthenticationToken auth = 
                new UsernamePasswordAuthenticationToken(
                    claims.getSubject(), 
                    null, 
                    extractAuthorities(claims)
                );
            
            
            return chain.filter(exchange)
                    .contextWrite(ReactiveSecurityContextHolder.withAuthentication(auth));
            
        } catch (Exception e) {
           
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    private Collection<? extends GrantedAuthority> extractAuthorities(Claims claims) {
        String role = (String) claims.get("role");
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }
}