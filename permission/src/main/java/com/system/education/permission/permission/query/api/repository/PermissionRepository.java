package com.system.education.permission.permission.query.api.repository;

import com.system.education.permission.permission.core.model.Permission;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PermissionRepository extends ReactiveMongoRepository<Permission, String> {
    Mono<Permission> findByPermissionCode(@Param("permissionCode") String permissionCode);
    Mono<Boolean> existsByPermissionCode(@Param("permissionCode") String permissionCode);
    Flux<Permission> findByPermissionTypeAndSemesterAndSchoolYear(@Param("permissionType") String permissionType, @Param("semester") String semester, @Param("schoolYear") String schoolYear);
    Flux<Permission> findByEstablishmentCode(@Param("establishmentCode") String establishmentCode);
    Flux<Permission> findByEstablishmentCodeAndSemesterAndSchoolYear(@Param("establishmentCode") String establishmentCode, @Param("semester") String semester, @Param("schoolYear") String schoolYear);
    Flux<Permission> findByStudentCodeAndSemesterAndSchoolYear(@Param("studentCode") String studentCode, @Param("semester") String semester, @Param("schoolYear") String schoolYear);
}
