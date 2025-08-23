package com.system.education.auth.account.query.api.repository;

import com.system.education.auth.account.core.model.UserAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserAccountRepository extends ReactiveMongoRepository<UserAccount, String> {
    Mono<Boolean> existsUserAccountByUserCode(@Param("userCode") String userCode);
    Mono<UserAccount> findUserAccountByUserCode(@Param("userCode") String userCode);
    Mono<Boolean> existsUserAccountByUsername(@Param("Username") String Username);
    Mono<UserAccount> findUserAccountByUsername(@Param("Username") String Username);
    Flux<UserAccount> findByEstablishmentCode(@Param("establishmentCode") String establishmentCode);
    Flux<UserAccount> findByEstablishmentCodeNot(@Param("establishmentCode") String establishmentCode);
}
