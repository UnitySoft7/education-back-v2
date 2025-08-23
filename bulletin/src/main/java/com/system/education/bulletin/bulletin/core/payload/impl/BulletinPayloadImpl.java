package com.system.education.bulletin.bulletin.core.payload.impl;

import com.system.education.bulletin.core.common.BulletinCode;
import com.system.education.bulletin.bulletin.core.payload.BulletinPayload;
import com.system.education.bulletin.bulletin.query.api.repository.BulletinRepositories;
import com.system.education.bulletin.bulletin.query.api.repository.BulletinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BulletinPayloadImpl implements BulletinPayload {
    private final BulletinRepository bulletinRepository;
    private final BulletinRepositories bulletinRepositories;

    /**
     * This method generates a unique code for the skill.
     * It first checks if there are any existing skills in the repository.
     * If there are no skills, it returns a default code "ESSKC10000001".
     * If there are existing skills, it retrieves the last skill's code and generates a new code based on it.
     *
     * @return A Mono containing the generated skill code.
     */
    @Override
    public Mono<String> getCode(){
        return bulletinRepository.count().flatMap(aLong -> {
            if (aLong == 0) {
                return Mono.just("ESBTC10000001");
            }
            else {
                Mono<String> code = bulletinRepositories.getLastBulletin()
                        .flatMap(bulletin -> Mono.just(bulletin.getBulletinCode()));
                return BulletinCode.generate(code);
            }
        });
    }

    /**
     * This method checks if a skill with the given code exists in the repository.
     * It uses the skillRepository to check for existence.
     *
     * @param bulletinCode The skill code of the skill to check.
     * @return A Mono containing true if the skill exists, false otherwise.
     */
    @Override
    public Mono<Boolean> isBulletinCodeExist(String bulletinCode) {
        return bulletinRepository.existsByBulletinCode(bulletinCode);
    }
}
