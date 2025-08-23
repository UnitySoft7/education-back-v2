package com.system.education.establishment.core.payload;

import com.system.education.establishment.core.dto.MessageResponse;
import reactor.core.publisher.Mono;

public interface EstablishmentPayload {
    Mono<String> getCode();

    Mono<Boolean> isEstablishmentNameExist(String establishmentName);

    Mono<Boolean> isEstablishmentCodeExist(String establishmentCode);

    Mono<Boolean> isEnableEstablishment(String establishmentCode);

    Mono<Boolean> isDisableEstablishment(String establishmentCode);

    Mono<MessageResponse> verifyPassword(String newPassword, String verifyPassword);
}
