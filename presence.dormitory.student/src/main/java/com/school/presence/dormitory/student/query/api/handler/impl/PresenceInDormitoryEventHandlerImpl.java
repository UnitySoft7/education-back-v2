package com.school.presence.dormitory.student.query.api.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.presence.dormitory.student.cmd.api.command.PresenceInDormitoryCreatedCommand;
import com.school.presence.dormitory.student.cmd.api.command.PresenceInDormitoryUpdatedCommand;
import com.school.presence.dormitory.student.cmd.api.command.PresenceQueryCommand;
import com.school.presence.dormitory.student.core.common.LogCreated;
import com.school.presence.dormitory.student.core.common.Status;
import com.school.presence.dormitory.student.core.dto.MessageResponse;
import com.school.presence.dormitory.student.core.model.PresenceInDormitory;
import com.school.presence.dormitory.student.core.payload.PresenceInDormitoryPayload;
import com.school.presence.dormitory.student.query.api.handler.PresenceInDormitoryEventHandler;
import com.school.presence.dormitory.student.query.api.repository.PresenceInDormitoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PresenceInDormitoryEventHandlerImpl implements PresenceInDormitoryEventHandler {
    private final PresenceInDormitoryRepository presenceInDormitoryRepository;
    private final PresenceInDormitoryPayload presenceInDormitoryPayload;
    private final ObjectMapper objectMapper;
    @Override
    public Mono<ResponseEntity<MessageResponse>> create(PresenceInDormitoryCreatedCommand command) {
        return presenceInDormitoryPayload.getCode().flatMap(code -> {
            List<String> valids = List.of("ABSENT", "PRESENT");
            List<String> invalidStatuses = new ArrayList<>();

            // 🔹 Vérification des statuts valides
            for (PresenceQueryCommand presence : command.presences()) {
                if (!valids.contains(presence.status())) {
                    invalidStatuses.add(presence.status());
                }
            }

            if (!invalidStatuses.isEmpty()) {
                return Mono.just(ResponseEntity
                        .badRequest()
                        .body(new MessageResponse(false,
                                "Statuts invalides trouvés: " + invalidStatuses + ". Les valides sont: " + valids)));
            }

            if (command.presences().isEmpty()) {
                return Mono.just(ResponseEntity
                        .badRequest()
                        .body(new MessageResponse(false, "La liste des présences est vide.")));
            }

            // 🔹 On traite chaque student pour récupérer le fullname
            return Flux.fromIterable(command.presences())
                    .flatMap(presence ->
                            presenceInDormitoryPayload.verifyESCSCode(presence.student())
                                    .flatMap(escsResponse -> {
//                                        if (!escsResponse.success()) {
//                                            return Mono.error(new RuntimeException(
//                                                    "Section year introuvable pour student : " + presence.student()));
//                                        }

                                        // 🔹 Injecter le fullname automatiquement
                                        String fullname = escsResponse.establishmentSectionClassStudentResponse().studentName();
                                        return Mono.just(new PresenceQueryCommand(
                                                presence.student(),
                                                fullname,
                                                presence.status()
                                        ));
                                    })
                    )
                    .collectList()
                    .flatMap(presencesWithNames -> {
                        try {
                            ObjectMapper mapper = new ObjectMapper();
                            String presenceJson = mapper.writeValueAsString(presencesWithNames);

                            // 🔹 Comptage automatique des présents/absents
                            double presentsCount = presencesWithNames.stream()
                                    .filter(p -> "PRESENT".equalsIgnoreCase(p.status()))
                                    .count();

                            double absentsCount = presencesWithNames.stream()
                                    .filter(p -> "ABSENT".equalsIgnoreCase(p.status()))
                                    .count();

                            double effective = presentsCount + absentsCount;

                            // 🔹 Construction de l'entité
                            PresenceInDormitory value = PresenceInDormitory.builder()
                                    .presenceInDormitoryId(UUID.randomUUID().toString())
                                    .presenceInDormitoryCode(code)
                                    .presence(presenceJson)
                                    .prof(command.prof())
                                    .status(Status.enable())
                                    .effective(0)
                                    .absents(absentsCount)
                                    .presents(presentsCount)
                                    .schoolYear("2025-2026")
                                    .trimester("1st Trimester")
                                    .logCreatedAt(LogCreated.At())
                                    .logCreatedDate(LogCreated.Date())
                                    .logCreatedMonth(LogCreated.Month())
                                    .logCreatedYear(LogCreated.Year())
                                    .build();


                            return presenceInDormitoryRepository.save(value)
                                    .then(Mono.just(ResponseEntity.ok(
                                            new MessageResponse(true, "Présences enregistrées avec succès")
                                    )));
                        } catch (JsonProcessingException e) {
                            return Mono.just(ResponseEntity
                                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .body(new MessageResponse(false, "Erreur de sérialisation JSON")));
                        }
                    })
                    .onErrorResume(ex -> Mono.just(ResponseEntity
                            .status(HttpStatus.NOT_FOUND)
                            .body(new MessageResponse(false, ex.getMessage()))));
        });
    }


    @Override
    public Mono<PresenceInDormitory> update(PresenceInDormitoryUpdatedCommand command) {
        return presenceInDormitoryRepository.findPresenceInDormitoryByPresenceInDormitoryCode(command.presenceInDormitoryCode()).flatMap(presenceInDormitory -> {
            presenceInDormitory.setPresence(command.ESCS());
            presenceInDormitory.setEffective(command.effective());
            presenceInDormitory.setAbsents(command.absents());
            presenceInDormitory.setPresents(command.presents());
            return presenceInDormitoryRepository.save(presenceInDormitory);
        });
    }
}
