package com.system.education.permission.permission.core.payload.impl;

import com.system.education.permission.core.common.PermissionCode;
import com.system.education.permission.permission.core.payload.PermissionPayload;
import com.system.education.permission.permission.query.api.repository.PermissionRepositories;
import com.system.education.permission.permission.query.api.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PermissionPayloadImpl implements PermissionPayload {
    private final PermissionRepository permissionRepository;
    private final PermissionRepositories permissionRepositories;

    /**
     * This method generates a unique code for the permission.
     * It first checks if there are any existing permissions in the repository.
     * If there are no permissions, it returns a default code "ESPMC10000001".
     * If there are existing permissions, it retrieves the last permission's code and generates a new code based on it.
     *
     * @return A Mono containing the generated permission code.
     */
    @Override
    public Mono<String> getCode(){
        return permissionRepository.count().flatMap(aLong -> {
            if (aLong == 0) {
                return Mono.just("ESPMC10000001");
            }
            else {
                Mono<String> code = permissionRepositories.getLastPermission()
                        .flatMap(permission -> Mono.just(permission.getPermissionCode()));
                return PermissionCode.generate(code);
            }
        });
    }

    /**
     * This method checks if a product with the given code exists in the repository.
     * It uses the productRepository to check for existence.
     *
     * @param productCode The product code of the product to check.
     * @return A Mono containing true if the product exists, false otherwise.
     */
    @Override
    public Mono<Boolean> isPermissionCodeExist(String productCode) {
        return permissionRepository.existsByPermissionCode(productCode);
    }
}
