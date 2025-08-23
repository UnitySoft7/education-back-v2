package com.system.education.auth.account.query.api.repository;

import com.system.education.auth.account.core.model.BlacklistedToken;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListedTokenRepository extends ReactiveMongoRepository<BlacklistedToken, String> {
}
