package com.system.education.parent.query.api.handler.impl;

import com.system.education.parent.cmd.api.command.AddParentCommand;
import com.system.education.parent.cmd.api.command.ParentCreatedCommand;
import com.system.education.parent.cmd.api.command.ParentUpdatedCommand;
import com.system.education.parent.cmd.api.command.UserAccountCreatedRequest;
import com.system.education.parent.core.common.LogCreated;
import com.system.education.parent.core.dto.MessageResponse;
import com.system.education.parent.core.model.Parent;
import com.system.education.parent.core.payload.ParentPayload;
import com.system.education.parent.query.api.handler.ParentEventHandler;
import com.system.education.parent.query.api.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ParentEventHandlerImpl implements ParentEventHandler {
    private final ParentRepository parentRepository;
    private final ParentPayload parentPayload;

    @Override
    public Mono<Parent> create(ParentCreatedCommand command) {
        return parentPayload.getCode()
                .flatMap(code -> {
                    Parent parent = Parent.builder()
                            .parentId(UUID.randomUUID().toString())
                            .parentCode(code)
                            .fullName(command.fullName())
                            .citizenId(command.citizenId())
                            .phoneNo(command.phoneNo())
                            .address(command.address())
                            .logCreatedAt(LogCreated.At())
                            .logCreatedMonth(LogCreated.Month())
                            .logCreatedYear(LogCreated.Year())
                            .logCreatedDate(LogCreated.Date())
                            .build();

                    UserAccountCreatedRequest request = new UserAccountCreatedRequest(parent.getParentCode(),
                            parent.getFullName(), command.username(), command.password(), command.verifyPassword(),
                            "PARENT", "PARENT", "PARENT");
                    WebClient webClient = WebClient.create();
                    var user = webClient.post()
                            .uri("http://127.0.0.1:9901/api/v1/education/user-account/create-user-account")
                            .bodyValue(request)
                            .retrieve()
                            .bodyToMono(MessageResponse.class);
                    return user.flatMap(userResponse -> {
                        if (userResponse.success()) {
                            return parentRepository.save(parent).flatMap(parentSave -> {
                                for (String studentCode : command.students()) {
                                    AddParentCommand addCommand = new AddParentCommand(
                                            studentCode, parentSave.getParentCode(), parent.getFullName());

                                    WebClient.create()
                                            .put()
//                                    .uri("https://establishment-section-class-s2sm.onrender.com/api/v1/education/student/add-parent")
                                            .uri("http://127.0.0.1:9910/api/v1/education/student/add-parent")
                                            .bodyValue(addCommand)
                                            .retrieve()
                                            .bodyToMono(MessageResponse.class)
                                            .subscribe();
                                }
                                return Mono.just(parentSave);
                            });
                        }
                        return null;
                    });
                });
    }

    @Override
    public Mono<Parent> update(ParentUpdatedCommand command) {
        return parentRepository.findByParentCode(command.parentCode())
                .flatMap(parent -> {
                    parent.setPhoneNo(command.phoneNo());
                    parent.setAddress(command.address());
                    return parentRepository.save(parent);
                });
    }
}
