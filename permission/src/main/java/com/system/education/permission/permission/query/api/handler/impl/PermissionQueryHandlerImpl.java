package com.system.education.permission.permission.query.api.handler.impl;

import com.system.education.permission.permission.query.api.handler.PermissionQueryHandler;
import com.system.education.permission.permission.core.model.Permission;
import com.system.education.permission.permission.query.api.query.PermissionByCodeAndSemesterQuery;
import com.system.education.permission.permission.query.api.query.PermissionByCodeQuery;
import com.system.education.permission.permission.query.api.query.PermissionByIdQuery;
import com.system.education.permission.permission.query.api.response.NameResponse;
import com.system.education.permission.permission.query.api.repository.PermissionRepository;
import com.system.education.permission.permission.query.api.response.PermissionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PermissionQueryHandlerImpl implements PermissionQueryHandler {
    private final PermissionRepository permissionRepository;

    /**
     * This method is used to get all permissions
     * @return a flux of permission response
     */
    @Override
    public Flux<PermissionResponse> getPermissions() {
        return permissionRepository.findAll()
                .flatMap(this::getPermissionResponse);
    }

    /**
     * This method is used to get a permission by ID
     * @param query the ID of the permission
     * @return a mono of permission response
     */
    @Override
    public Mono<PermissionResponse> getPermissionById(PermissionByIdQuery query) {
        return permissionRepository.findById(query.id())
                .flatMap(this::getPermissionResponse);
    }

    /**
     * This method is used to get a permission by permission code
     * @param query the permission code of the permission
     * @return a mono of permission response
     */
    @Override
    public Mono<PermissionResponse> getPermissionByCode(PermissionByCodeQuery query) {
        return permissionRepository.findByPermissionCode(query.code())
                .flatMap(this::getPermissionResponse);
    }

    /**
     * This method is used to get a permission by establishment code
     * @param query the establishment code of the establishment
     * @return a mono of permission response
     */
    @Override
    public Flux<PermissionResponse> getPermissionByEstablishment(PermissionByCodeQuery query) {
        return permissionRepository.findByEstablishmentCode(query.code())
                .flatMap(this::getPermissionResponse);
    }

    @Override
    public Flux<PermissionResponse> getPermissionByEstablishmentAndSemester(PermissionByCodeAndSemesterQuery query) {
        return permissionRepository.findByEstablishmentCodeAndSemesterAndSchoolYear(query.code(), query.semester(), query.schoolYear())
                .flatMap(this::getPermissionResponse);
    }

    @Override
    public Flux<PermissionResponse> getPermissionByStudentAndSemester(PermissionByCodeAndSemesterQuery query) {
        return permissionRepository.findByStudentCodeAndSemesterAndSchoolYear(query.code(), query.semester(), query.schoolYear())
                .flatMap(this::getPermissionResponse);
    }

    @Override
    public Flux<PermissionResponse> getPermissionByPermissionTypeAndSemester(PermissionByCodeAndSemesterQuery query) {
        return permissionRepository.findByPermissionTypeAndSemesterAndSchoolYear(query.code(), query.semester(), query.schoolYear())
                .flatMap(this::getPermissionResponse);
    }

    /**
     * This method is used to convert a permission to a permission response
     * @param permission the permission to convert
     * @return the driver response
     */
    private Mono<PermissionResponse> getPermissionResponse(Permission permission) {
        return Mono.just(
                new PermissionResponse(permission.getPermissionId(),
                        permission.getPermissionCode(), permission.getPermissionType(),
                        permission.getDescription(), permission.getStartOn(),
                        permission.getEndOn(), permission.getStudentCode(),
                        permission.getStudentName(), permission.getSemester(),
                        permission.getSchoolYear(), permission.getEstablishmentCode(),
                        permission.getEstablishmentName(), permission.getLogCreatedAt(),
                        permission.getLogCreatedMonth(), permission.getLogCreatedYear(),
                        permission.getLogCreatedDate())
        );
    }

    @Override
    public List<NameResponse> getName() {
        List<NameResponse> nameResponses = new ArrayList<>();
        nameResponses.add(new NameResponse("SICKNESS", "SICKNESS"));
        nameResponses.add(new NameResponse("PRAYER", "PRAYER"));
        nameResponses.add(new NameResponse("VOYAGE", "VOYAGE"));
        nameResponses.add(new NameResponse("RETURN HOME", "RETURN HOME"));
        nameResponses.add(new NameResponse("OTHERS", "OTHERS"));
        return nameResponses;
    }
}
