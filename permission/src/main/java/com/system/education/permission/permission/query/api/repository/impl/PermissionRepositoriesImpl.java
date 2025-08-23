package com.system.education.permission.permission.query.api.repository.impl;

import com.system.education.permission.permission.core.model.Permission;
import com.system.education.permission.permission.query.api.repository.PermissionRepositories;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class PermissionRepositoriesImpl implements PermissionRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public PermissionRepositoriesImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<Permission> getLastPermission() {
        Query query = new Query();
//        query.addCriteria(Criteria.where("id").exists(true));
        query.with(Sort.by(Sort.Direction.DESC, "permissionCode"));
        query.limit(1);

        return reactiveMongoTemplate.find(query, Permission.class)
                .next();
    }
}
