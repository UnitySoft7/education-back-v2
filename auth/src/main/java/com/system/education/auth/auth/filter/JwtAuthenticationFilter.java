package com.system.education.auth.auth.filter;

import com.system.education.auth.auth.logout.JwtBlacklistService;
import com.system.education.auth.auth.util.ReactiveUserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements WebFilter {
    private final JwtTokenProvider tokenProvider;
    private final ReactiveUserDetailsServiceImpl userDetailsService;
    private final JwtBlacklistService jwtBlacklistService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String token = getTokenFromRequest(exchange);

        if (token == null || token.isBlank()) {
            return chain.filter(exchange);
        }

        return jwtBlacklistService.isTokenBlacklisted(token)
                .flatMap(isBlacklisted -> {
                    if (isBlacklisted) {
                        exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    }
                    if (!tokenProvider.validateToken(token)) {
                        System.out.println(token);
                        return chain.filter(exchange);
                    }

                    String username = tokenProvider.getUsernameFromJWT(token);

                    return userDetailsService.findByUsername(username)
                            .flatMap(userDetails -> {
                                AbstractAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                        userDetails, null, userDetails.getAuthorities());
                                return chain.filter(exchange)
                                        .contextWrite(org.springframework.security.core.context.ReactiveSecurityContextHolder
                                                .withSecurityContext(Mono.just(new SecurityContextImpl(authToken))));
                            });
                });
    }

    private String getTokenFromRequest(ServerWebExchange exchange) {
        String bearer = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (bearer != null && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}
