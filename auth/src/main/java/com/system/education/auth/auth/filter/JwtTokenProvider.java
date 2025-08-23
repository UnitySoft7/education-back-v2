package com.system.education.auth.auth.filter;

import com.system.education.auth.auth.command.UserDto;
import com.system.education.auth.auth.logout.JwtBlacklistService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String JWT_SECRET;

    @Value("${app.jwt-expiration-milliseconds}")
    private int JWT_EXPIRATION_MS;

    private final JwtBlacklistService jwtBlacklistService;

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SECRET.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + JWT_EXPIRATION_MS);

        var authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return Jwts.builder()
                .setSubject(username)
                .claim("authorities", authorities)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = parseClaims(token);
        return claims.getSubject();
    }

    public List<String> getAuthoritiesFromToken(String token) {
        Claims claims = parseClaims(token);

        Object raw = claims.get("authorities");
        if (raw instanceof List<?>) {
            return ((List<?>) raw).stream()
                    .filter(Objects::nonNull)
                    .map(Object::toString)
                    .toList();
        }
        return Collections.emptyList();
    }

    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            LOGGER.error("JWT validation error: {}", e.getMessage());
            return false;
        }
    }

    public Mono<UserDto> validateTokenGateway(String token) {
        return jwtBlacklistService.isTokenBlacklisted(token)
                .flatMap(isBlacklisted -> {
                    if (isBlacklisted) {
                        LOGGER.warn("Token is blacklisted");
                        return Mono.error(new RuntimeException("Token is blacklisted"));
                    }
                    try {
                        Claims claims = parseClaims(token);
                        String login = claims.getSubject();
                        return Mono.just(new UserDto("username", login, token));
                    } catch (JwtException e) {
                        LOGGER.error("Invalid JWT token: {}", e.getMessage());
                        return Mono.error(new RuntimeException("Invalid JWT token"));
                    }
                });
    }
}
