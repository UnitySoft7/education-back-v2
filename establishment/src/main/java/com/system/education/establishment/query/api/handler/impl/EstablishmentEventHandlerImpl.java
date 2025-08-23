package com.system.education.establishment.query.api.handler.impl;

import com.system.education.establishment.cmd.api.command.ChangeEstablishmentStateCommand;
import com.system.education.establishment.cmd.api.command.EstablishmentCreatedCommand;
import com.system.education.establishment.cmd.api.command.EstablishmentUpdatedCommand;
import com.system.education.establishment.cmd.api.command.UserAccountCreatedRequest;
import com.system.education.establishment.core.common.EstablishmentState;
import com.system.education.establishment.core.common.LogCreated;
import com.system.education.establishment.core.dto.MessageResponse;
import com.system.education.establishment.core.model.Establishment;
import com.system.education.establishment.core.payload.EstablishmentPayload;
import com.system.education.establishment.query.api.handler.EstablishmentEventHandler;
import com.system.education.establishment.query.api.repository.EstablishmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EstablishmentEventHandlerImpl implements EstablishmentEventHandler {
    private final EstablishmentRepository establishmentRepository;
    private final EstablishmentPayload establishmentPayload;
    String uploadDir = "establishment/src/main/resources/static/uploads";
    private final Path rootImage = Paths.get(uploadDir);

    @Override
    public Mono<Establishment> create(EstablishmentCreatedCommand command) {
        return establishmentPayload.getCode()
                .flatMap(code -> {
                    if (!Files.exists(rootImage)) {
                        try {
                            Files.createDirectories(rootImage);
                        } catch (IOException e) {
                            return Mono.error(new RuntimeException("Erreur création dossier"));
                        }
                    }

                    String logoName = UUID.randomUUID() + "_" + command.logo().filename();
                    Path destinationLogo = rootImage.resolve(logoName).normalize().toAbsolutePath();
                    Mono<Void> saveLogo = command.logo().transferTo(destinationLogo);

                    Establishment establishment = Establishment.builder()
                            .establishmentId(UUID.randomUUID().toString())
                            .establishmentCode(code)
                            .establishmentName(command.establishmentName())
                            .nickName(command.nickName())
                            .nif(command.nif())
                            .rc(command.rc())
                            .phoneNo(command.phoneNo())
                            .email(command.email())
                            .site(command.site())
                            .state(EstablishmentState.enable())
                            .logoPath(destinationLogo.toString())
                            .province(command.province())
                            .commune(command.commune())
                            .zone(command.zone())
                            .quarter(command.quarter())
                            .locality(command.locality())
                            .logCreatedAt(LogCreated.At())
                            .logCreatedMonth(LogCreated.Month())
                            .logCreatedYear(LogCreated.Year())
                            .logCreatedDate(LogCreated.Date())
                            .logModifiedDate(LogCreated.Date())
                            .build();
                    UserAccountCreatedRequest request = new UserAccountCreatedRequest(establishment.getEstablishmentCode(),
                            establishment.getEstablishmentName(), command.username(), command.password(), command.verifyPassword(),
                            "SUPER_ADMIN", establishment.getEstablishmentName(), establishment.getEstablishmentCode());
                    WebClient webClient = WebClient.create();
                    var user = webClient.post()
                            .uri("http://127.0.0.1:9901/api/v1/education/user-account/create-user-account")
                            .bodyValue(request)
                            .retrieve()
                            .bodyToMono(MessageResponse.class);
                    return user.flatMap(userResponse -> {
                        if (userResponse.success()) {
                            return Mono.when(saveLogo)
                                    .then(establishmentRepository.save(establishment));
                        }
                        return null;
                    });
                });
    }

    @Override
    public Mono<Establishment> enable(ChangeEstablishmentStateCommand command) {
        return establishmentRepository.findByEstablishmentCode(command.establishmentCode())
                .flatMap(establishment -> {
                    establishment.setState(EstablishmentState.enable());
                    return establishmentRepository.save(establishment);
                });
    }

    @Override
    public Mono<Establishment> disable(ChangeEstablishmentStateCommand command) {
        return establishmentRepository.findByEstablishmentCode(command.establishmentCode())
                .flatMap(establishment -> {
                    establishment.setState(EstablishmentState.disable());
                    return establishmentRepository.save(establishment);
                });
    }

    @Override
    public Mono<Establishment> update(EstablishmentUpdatedCommand command) {
        return establishmentRepository.findByEstablishmentCode(command.establishmentCode())
                .flatMap(establishment -> {
                    establishment.setNickName(command.nickName());
                    establishment.setPhoneNo(command.phoneNo());
                    establishment.setEmail(command.email());
                    establishment.setSite(command.site());
                    establishment.setProvince(command.province());
                    establishment.setCommune(command.commune());
                    establishment.setZone(command.zone());
                    establishment.setQuarter(command.quarter());
                    establishment.setLocality(command.locality());
                    return establishmentRepository.save(establishment);
                });
    }


}
