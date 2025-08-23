package com.system.education.establishment.core.payload.impl;

import com.system.education.establishment.core.common.EstablishmentCode;
import com.system.education.establishment.core.common.EstablishmentState;
import com.system.education.establishment.core.dto.MessageResponse;
import com.system.education.establishment.core.payload.EstablishmentPayload;
import com.system.education.establishment.query.api.repository.EstablishmentRepositories;
import com.system.education.establishment.query.api.repository.EstablishmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class EstablishmentPayloadImpl implements EstablishmentPayload {
    private final EstablishmentRepository establishmentRepository;
    private final EstablishmentRepositories establishmentRepositories;

    /**
     * This method generates a unique code for the establishment.
     * It first checks if there are any existing establishments in the repository.
     * If there are no establishments, it returns a default code "ESEC10000001".
     * If there are existing establishments, it retrieves the last establishment's code and generates a new code based on it.
     *
     * @return A Mono containing the generated establishment code.
     */
    @Override
    public Mono<String> getCode(){
        return establishmentRepository.count().flatMap(aLong -> {
            if (aLong == 0) {
                return Mono.just("ESEC10000001");
            }
            else {
                Mono<String> code = establishmentRepositories.getLastEstablishment()
                        .flatMap(establishment -> Mono.just(establishment.getEstablishmentCode()));
                return EstablishmentCode.generate(code);
            }
        });
    }

    /**
     * This method checks if an establishment with the given name exists in the repository.
     * It uses the establishmentRepository to check for existence.
     *
     * @param establishmentName The establishment name of the establishment to check.
     * @return A Mono containing true if the establishment exists, false otherwise.
     */
    @Override
    public Mono<Boolean> isEstablishmentNameExist(String establishmentName) {
        return establishmentRepository.existsByEstablishmentName(establishmentName);
    }

    /**
     * This method checks if an establishment with the given code exists in the repository.
     * It uses the establishmentRepository to check for existence.
     *
     * @param establishmentCode The establishment code of the establishment to check.
     * @return A Mono containing true if the establishment exists, false otherwise.
     */
    @Override
    public Mono<Boolean> isEstablishmentCodeExist(String establishmentCode) {
        return establishmentRepository.existsByEstablishmentCode(establishmentCode);
    }

    /**
     * This method checks if an establishment with the given establishment code is in the enable state.
     * It uses the establishmentRepository to check for existence.
     *
     * @param establishmentCode The establishment code of the establishment to check.
     * @return A Mono containing true if the establishment is in the enable state, false otherwise.
     */
    @Override
    public Mono<Boolean> isEnableEstablishment(String establishmentCode) {
        return establishmentRepository.existsByEstablishmentCodeAndState(establishmentCode, EstablishmentState.enable());
    }

    /**
     * This method checks if an establishment with the given establishment code is in the disable state.
     * It uses the establishmentRepository to check for existence.
     *
     * @param establishmentCode The establishment code of the establishment to check.
     * @return A Mono containing true if the establishment is in the disable state, false otherwise.
     */
    @Override
    public Mono<Boolean> isDisableEstablishment(String establishmentCode) {
        return establishmentRepository.existsByEstablishmentCodeAndState(establishmentCode, EstablishmentState.disable());
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
