package com.system.education.auth.role.query.api.repository;

import com.system.education.auth.role.core.model.Role;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface RoleRepository extends ReactiveMongoRepository<Role, String> {
    Mono<Boolean> existsRoleByRoleNameAndEnterpriseCode(@Param(value = "roleName") String roleName, @Param(value = "enterpriseCode") String enterpriseCode);
    Mono<Role> findRoleByRoleNameAndEnterpriseCode(@Param(value = "roleName") String roleName, @Param(value = "enterpriseCode") String enterpriseCode);
    Flux<Role> findRoleByEnterpriseCodeNot(@Param(value = "enterpriseCode") String enterpriseCode);
    Flux<Role> findRoleByEnterpriseCode(@Param(value = "enterprise") String enterprise);
}
