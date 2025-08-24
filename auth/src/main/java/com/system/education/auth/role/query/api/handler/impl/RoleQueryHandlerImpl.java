package com.system.education.auth.role.query.api.handler.impl;

import com.system.education.auth.role.core.model.Role;
import com.system.education.auth.role.core.payload.RolePayload;
import com.system.education.auth.role.core.util.Permissions;
import com.system.education.auth.role.query.api.handler.RoleQueryHandler;
import com.system.education.auth.role.query.api.query.RoleByCodeQuery;
import com.system.education.auth.role.query.api.query.RoleByIdQuery;
import com.system.education.auth.role.query.api.repository.RoleRepository;
import com.system.education.auth.role.query.api.response.GetRoleNameResponse;
import com.system.education.auth.role.query.api.response.NameResponse;
import com.system.education.auth.role.query.api.response.RoleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class RoleQueryHandlerImpl implements RoleQueryHandler {
    private final RoleRepository roleRepository;
    private final RolePayload rolePayload;

    @Override
    public Flux<RoleResponse> findAllRole() {
        return roleRepository.findAll().flatMap(this::getRole);
    }

    @Override
    public Flux<RoleResponse> findAllRolesNotGlobal() {
        return roleRepository.findRoleByEnterpriseCodeNot("GLOBAL").flatMap(this::getRole);
    }

    @Override
    public Flux<RoleResponse> findAllRolesGlobal() {
        return roleRepository.findRoleByEnterpriseCode("GLOBAL").flatMap(this::getRole);
    }

    @Override
    public Mono<RoleResponse> findRoleByID(RoleByIdQuery query) {
        return roleRepository.findById(query.roleId())
                .flatMap(this::getRole);
    }

    @Override
    public Flux<RoleResponse> findRolesByEnterprise(RoleByCodeQuery query) {
        return roleRepository.findRoleByEnterpriseCode(query.code())
                .flatMap(this::getRole);
    }

    private Mono<RoleResponse> getRole(Role role) {
        return Mono.just(new RoleResponse(role.getRoleId(), role.getRoleName(),
                rolePayload.stringToList(role.getPermissions()),
                role.getEnterpriseName(), role.getEnterpriseCode()));
    }

    @Override
    public Mono<List<NameResponse>> getAllPermissionNames() {
        List<NameResponse> permissions = new ArrayList<>();
        permissions.add(new NameResponse(Permissions.user_view));
        permissions.add(new NameResponse(Permissions.user_create));
        permissions.add(new NameResponse(Permissions.user_update));
        permissions.add(new NameResponse(Permissions.user_enable));
        permissions.add(new NameResponse(Permissions.user_disable));

        permissions.add(new NameResponse(Permissions.role_view));
        permissions.add(new NameResponse(Permissions.role_create));
        permissions.add(new NameResponse(Permissions.role_update));

        permissions.add(new NameResponse(Permissions.all));

        permissions.add(new NameResponse(Permissions.bed_create));
        permissions.add(new NameResponse(Permissions.bed_update));
        permissions.add(new NameResponse(Permissions.bed_view));

        permissions.add(new NameResponse(Permissions.bullitin_create));
        permissions.add(new NameResponse(Permissions.bullitin_view));
        permissions.add(new NameResponse(Permissions.bullitin_update));


        permissions.add(new NameResponse(Permissions.cafetaria_create));

        permissions.add(new NameResponse(Permissions.cafetaria_update));
        permissions.add(new NameResponse(Permissions.cafetaria_view));


        permissions.add(new NameResponse(Permissions.attri_course_class_create));
        permissions.add(new NameResponse(Permissions.attri_course_class_view));
        permissions.add(new NameResponse(Permissions.attri_course_class_update));


        permissions.add(new NameResponse(Permissions.classroom_create));
        permissions.add(new NameResponse(Permissions.classroom_update));
        permissions.add(new NameResponse(Permissions.classroom_view));

        permissions.add(new NameResponse(Permissions.course_create));
        permissions.add(new NameResponse(Permissions.course_view));
        permissions.add(new NameResponse(Permissions.course_update));

        permissions.add(new NameResponse(Permissions.attri_dormitory_create));
        permissions.add(new NameResponse(Permissions.attri_dormitory_update));
        permissions.add(new NameResponse(Permissions.attri_dormitory_view));

        permissions.add(new NameResponse(Permissions.dormitory_create));
        permissions.add(new NameResponse(Permissions.dormitory_view));
        permissions.add(new NameResponse(Permissions.dormitory_update));

        permissions.add(new NameResponse(Permissions.dormitory_student_bed_press_create));
        permissions.add(new NameResponse(Permissions.dormitory_student_bed_press_update));
        permissions.add(new NameResponse(Permissions.dormitory_student_bed_press_view));

        permissions.add(new NameResponse(Permissions.establishment_create));
        permissions.add(new NameResponse(Permissions.establishment_disable));
        permissions.add(new NameResponse(Permissions.establishment_enable));
        permissions.add(new NameResponse(Permissions.establishment_view));
        permissions.add(new NameResponse(Permissions.establishment_update));

        permissions.add(new NameResponse(Permissions.evaluate_create));
        permissions.add(new NameResponse(Permissions.evaluate_view));
        permissions.add(new NameResponse(Permissions.evaluate_update));

        permissions.add(new NameResponse(Permissions.exam_create));
        permissions.add(new NameResponse(Permissions.exam_update));
        permissions.add(new NameResponse(Permissions.exam_view));

        permissions.add(new NameResponse(Permissions.examination_create));
        permissions.add(new NameResponse(Permissions.examination_view));
        permissions.add(new NameResponse(Permissions.examination_update));

        permissions.add(new NameResponse(Permissions.infirmary_create));
        permissions.add(new NameResponse(Permissions.infirmary_update));
        permissions.add(new NameResponse(Permissions.infirmary_view));

        permissions.add(new NameResponse(Permissions.minos_create));
        permissions.add(new NameResponse(Permissions.minos_update));
        permissions.add(new NameResponse(Permissions.minos_view));

        permissions.add(new NameResponse(Permissions.parent_create));
        permissions.add(new NameResponse(Permissions.parent_update));
        permissions.add(new NameResponse(Permissions.parent_view));

        permissions.add(new NameResponse(Permissions.permission_create));
        permissions.add(new NameResponse(Permissions.permission_view));
        permissions.add(new NameResponse(Permissions.permission_update));

        permissions.add(new NameResponse(Permissions.press_create));
        permissions.add(new NameResponse(Permissions.press_update));
        permissions.add(new NameResponse(Permissions.press_view));

        permissions.add(new NameResponse(Permissions.schedule_create));
        permissions.add(new NameResponse(Permissions.schedule_update));
        permissions.add(new NameResponse(Permissions.schedule_view));

        permissions.add(new NameResponse(Permissions.section_create));
        permissions.add(new NameResponse(Permissions.section_update));
        permissions.add(new NameResponse(Permissions.section_view));

        permissions.add(new NameResponse(Permissions.skill_create));
        permissions.add(new NameResponse(Permissions.skill_update));
        permissions.add(new NameResponse(Permissions.skill_view));

        permissions.add(new NameResponse(Permissions.student_create));
        permissions.add(new NameResponse(Permissions.student_update));
        permissions.add(new NameResponse(Permissions.student_view));

        permissions.add(new NameResponse(Permissions.teacher_create));
        permissions.add(new NameResponse(Permissions.teacher_update));
        permissions.add(new NameResponse(Permissions.teacher_view));

        return Mono.just(permissions);
    }

    @Override
    public Mono<List<NameResponse>> getAllGlobalPermissionNames() {
        List<NameResponse> permissions = new ArrayList<>();
        permissions.add(new NameResponse(Permissions.user_global));
        permissions.add(new NameResponse(Permissions.role_global));
        permissions.add(new NameResponse(Permissions.all_global));
        return Mono.just(permissions);
    }
}
