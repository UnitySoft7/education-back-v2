package com.system.education.auth.auth.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthoritiesLoggingAfterFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return chain.filter(exchange)
                .then(
                        ReactiveSecurityContextHolder.getContext()
                                .map(SecurityContext::getAuthentication)
                                .doOnNext(authentication -> {
                                    if (authentication != null) {
                                        log.info("User '{}' is successfully authenticated and has the authorities: {}",
                                                authentication.getName(), authentication.getAuthorities());
                                    }
                                })
                                .then()
                );
    }
}
