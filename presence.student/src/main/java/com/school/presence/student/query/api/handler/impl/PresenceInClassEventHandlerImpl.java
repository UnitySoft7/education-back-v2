package com.school.presence.student.query.api.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.presence.student.cmd.api.command.PresenceInClassCreatedCommand;
import com.school.presence.student.cmd.api.command.PresenceInClassUpdatedCommand;
import com.school.presence.student.cmd.api.command.PresenceQueryCommand;
import com.school.presence.student.cmd.api.command.PresenceStudentDailyCreatedCommand;
import com.school.presence.student.core.common.LogCreated;
import com.school.presence.student.core.common.Status;
import com.school.presence.student.core.dto.MessageResponse;
import com.school.presence.student.core.model.PresenceInClass;
import com.school.presence.student.core.payload.PresenceInClassPayload;
import com.school.presence.student.query.api.handler.PresenceInClassEventHandler;
import com.school.presence.student.query.api.repository.PresenceInClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PresenceInClassEventHandlerImpl implements PresenceInClassEventHandler {
    private final PresenceInClassRepository presenceInClassRepository;
    private final PresenceInClassPayload presenceInClassPayload;
    private final ObjectMapper objectMapper;
    @Override
    public Mono<ResponseEntity<MessageResponse>> create(PresenceInClassCreatedCommand command) {
        return presenceInClassPayload.getCode().flatMap(code -> {
            List<String> valids = List.of("ABSENT", "PRESENT");
            List<String> invalidStatuses = new ArrayList<>();

            for (PresenceQueryCommand presence : command.presences()) {
                if (!valids.contains(presence.status())) {
                    invalidStatuses.add(presence.status());
                }
            }

            if (!invalidStatuses.isEmpty()) {
                return Mono.just(ResponseEntity.badRequest().body(
                        new MessageResponse(false, "Statuts invalides trouvés: " + invalidStatuses + ". Les valides sont: " + valids)
                ));
            }

            if (command.presences().isEmpty()) {
                return Mono.just(ResponseEntity.badRequest().body(
                        new MessageResponse(false, "La liste des présences est vide.")
                ));
            }

            return Flux.fromIterable(command.presences())
                    .flatMap(presence ->
                            presenceInClassPayload.verifyESCSCode(presence.studentCode())
                                    .flatMap(escsResponse -> {
                                        String fullname = escsResponse.establishmentSectionClassStudentResponse().studentName();
                                        return Mono.just(new PresenceQueryCommand(
                                                presence.studentCode(),
                                                fullname,
                                                presence.status()
                                        ));
                                    })
//                                    .switchIfEmpty(Mono.error(new RuntimeException("studentCode introuvable : " + presence.studentCode())))
                    )
                    .collectList()
                    .flatMap(presencesWithNames -> {
                        try {
                            ObjectMapper mapper = new ObjectMapper();
                            String presenceJson = mapper.writeValueAsString(presencesWithNames);

                            double presentsCount = presencesWithNames.stream()
                                    .filter(p -> "PRESENT".equalsIgnoreCase(p.status()))
                                    .count();

                            double absentsCount = presencesWithNames.stream()
                                    .filter(p -> "ABSENT".equalsIgnoreCase(p.status()))
                                    .count();

                            double effective = presentsCount + absentsCount;

                            PresenceInClass value = PresenceInClass.builder()
                                    .presenceInClassId(UUID.randomUUID().toString())
                                    .presenceInClassCode(code)
                                    .presence(presenceJson)
                                    .pointer(command.pointer())
                                    .status(Status.enable())
                                    .effective(effective)
                                    .absents(absentsCount)
                                    .presents(presentsCount)
                                    .scheduleCode(command.scheduleCode())
                                    .schoolYear("2025-2026")
                                    .trimester("1st Trimester")
                                    .logCreatedAt(LogCreated.At())
                                    .logCreatedDate(LogCreated.Date())
                                    .logCreatedMonth(LogCreated.Month())
                                    .logCreatedYear(LogCreated.Year())
                                    .build();

                            return presenceInClassRepository.save(value)
                                    .flatMap(saved -> {
                                        if (saved == null || saved.getPresenceInClassId() == null) {
                                            return Mono.error(new RuntimeException("Erreur : échec de la sauvegarde de la présence."));
                                        }

                                        // Appel microservice après save réussi
                                        return Flux.fromIterable(presencesWithNames)
                                                .flatMap(presence -> {
                                                    PresenceStudentDailyCreatedCommand dailyCommand = new PresenceStudentDailyCreatedCommand(
                                                            code,
                                                            presence.status(),
                                                            saved.getPointer(),
                                                            presence.studentCode(),
                                                            saved.getScheduleCode(),
                                                            saved.getEffective(),
                                                            saved.getAbsents(),
                                                            saved.getPresents(),
                                                            saved.getSchoolYear(),
                                                            saved.getTrimester(),
                                                            saved.getStatus()
                                                    );

                                                    WebClient webClient = WebClient.create();
//
                                                    return webClient.post()
                                                            .uri("http://127.0.0.1:9928/api/v1/education/presence-student-daily/create-presence-student-daily")
                                                            .bodyValue(dailyCommand)
                                                            .retrieve()
                                                            .bodyToMono(Void.class)
                                                            .onErrorResume(ex -> Mono.error(new RuntimeException("Erreur microservice : " + ex.getMessage())));
                                                })
                                                .then(Mono.just(ResponseEntity.ok(
                                                        new MessageResponse(true, "Présences enregistrées avec succès")
                                                )));
                                    });

                        } catch (JsonProcessingException e) {
                            return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .body(new MessageResponse(false, "Erreur de sérialisation JSON")));
                        }
                    })
                    .onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(new MessageResponse(false, "Erreur durant l'enregistrement : " + ex.getMessage()))));
        });
    }

//                                    .then(Mono.just(ResponseEntity.ok(
//                                            new MessageResponse(true, "Présences enregistrées avec succès")
//                                    )));
//                        } catch (JsonProcessingException e) {
//                            return Mono.just(ResponseEntity
//                                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                                    .body(new MessageResponse(false, "Erreur de sérialisation JSON")));
//                        }
//                    })
//                    .onErrorResume(ex -> Mono.just(ResponseEntity
//                            .status(HttpStatus.NOT_FOUND)
//                            .body(new MessageResponse(false, ex.getMessage()))));
//        });
//    }


    @Override
    public Mono<PresenceInClass> update(PresenceInClassUpdatedCommand command) {
        return presenceInClassRepository.findPresenceInClassByPresenceInClassCode(command.presenceInClassCode()).flatMap(presenceInClass -> {
            presenceInClass.setPresence(command.ESCS());
            presenceInClass.setEffective(command.effective());
            presenceInClass.setAbsents(command.absents());
            presenceInClass.setPresents(command.presents());
            return presenceInClassRepository.save(presenceInClass);
        });
    }
}
