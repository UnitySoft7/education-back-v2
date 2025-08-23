package com.system.education.permission.permission.query.api.repository;

import com.system.education.permission.permission.core.model.Permission;
import reactor.core.publisher.Mono;

public interface PermissionRepositories {
    Mono<Permission> getLastPermission();
}
