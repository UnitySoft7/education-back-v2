package com.system.education.parent.core.payload.impl;

import com.system.education.parent.core.common.ParentCode;
import com.system.education.parent.core.dto.MessageResponse;
import com.system.education.parent.core.payload.ParentPayload;
import com.system.education.parent.query.api.repository.ParentRepositories;
import com.system.education.parent.query.api.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ParentPayloadImpl implements ParentPayload {
    private final ParentRepository parentRepository;
    private final ParentRepositories parentRepositories;

    /**
     * This method generates a unique code for the parent.
     * It first checks if there are any existing parents in the repository.
     * If there are no parents, it returns a default code "ESPC10000001".
     * If there are existing parents, it retrieves the last parent's code and generates a new code based on it.
     *
     * @return A Mono containing the generated parent code.
     */
    @Override
    public Mono<String> getCode(){
        return parentRepository.count().flatMap(aLong -> {
            if (aLong == 0) {
                return Mono.just("ESPC10000001");
            }
            else {
                Mono<String> code = parentRepositories.getLastParent()
                        .flatMap(parent -> Mono.just(parent.getParentCode()));
                return ParentCode.generate(code);
            }
        });
    }

    /**
     * This method checks if an parent with the given code exists in the repository.
     * It uses the parentRepository to check for existence.
     *
     * @param parentCode The parent code of the parent to check.
     * @return A Mono containing true if the parent exists, false otherwise.
     */
    @Override
    public Mono<Boolean> isParentCodeExist(String parentCode) {
        return parentRepository.existsByParentCode(parentCode);
    }

    @Override
    public Mono<MessageResponse> verifyPassword(String newPassword, String verifyPassword) {
        if (!Objects.equals(newPassword, verifyPassword)){
            return Mono.just(new MessageResponse( false, "Les mots de passe ne correspondent pas"));
        } else {
            return Mono.just(new MessageResponse(true, "OK"));
        }
    }
}
