package com.system.education.auth.auth.logout;

import com.system.education.auth.account.core.model.BlacklistedToken;
import com.system.education.auth.account.query.api.repository.BlackListedTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class JwtBlacklistService {
    private final BlackListedTokenRepository tokenRepository;

    public Mono<BlacklistedToken> blacklistToken(String token) {
        BlacklistedToken blacklistedToken = new BlacklistedToken(token, System.currentTimeMillis());
        return tokenRepository.save(blacklistedToken);
    }

    public Mono<Boolean> isTokenBlacklisted(String token) {
        return tokenRepository.existsById(token);
    }

    @Scheduled(fixedRate = 60 * 1000)
    public void cleanupExpiredTokens() {
        long currentTime = System.currentTimeMillis();
        long expirationTime = 2 * 60 * 1000;

        tokenRepository.findAll()
                .filter(token -> currentTime - token.getTimestamp() > expirationTime)
                .flatMap(tokenRepository::delete)
                .subscribe();
    }
}
