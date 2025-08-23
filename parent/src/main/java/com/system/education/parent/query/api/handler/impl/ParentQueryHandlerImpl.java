package com.system.education.parent.query.api.handler.impl;

import com.system.education.parent.core.model.Parent;
import com.system.education.parent.query.api.handler.ParentQueryHandler;
import com.system.education.parent.query.api.query.ParentByCodeQuery;
import com.system.education.parent.query.api.query.ParentByIdQuery;
import com.system.education.parent.query.api.repository.ParentRepository;
import com.system.education.parent.query.api.response.ParentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ParentQueryHandlerImpl implements ParentQueryHandler {
    private final ParentRepository parentRepository;

    /**
     * This method is used to get all parents
     * @return a flux of parent response
     */
    @Override
    public Flux<ParentResponse> getParents() {
        return parentRepository.findAll()
                .flatMap(this::getParentResponse);
    }

    /**
     * This method is used to get a parent by ID
     * @param query the ID of the parent
     * @return a mono of parent response
     */
    @Override
    public Mono<ParentResponse> getParentById(ParentByIdQuery query) {
        return parentRepository.findById(query.id())
                .flatMap(this::getParentResponse);
    }

    /**
     * This method is used to get a parent by parent code
     * @param query the parent code of the parent
     * @return a mono of parent response
     */
    @Override
    public Mono<ParentResponse> getParentByCode(ParentByCodeQuery query) {
        return parentRepository.findByParentCode(query.code())
                .flatMap(this::getParentResponse);
    }

    /**
     * This method is used to convert a parent to a parent response
     * @param parent the parent to convert
     * @return the driver response
     */
    private Mono<ParentResponse> getParentResponse(Parent parent) {
        return Mono.just(
                new ParentResponse(parent.getParentId(),
                        parent.getParentCode(), parent.getFullName(),
                        parent.getCitizenId(), parent.getPhoneNo(),
                        parent.getAddress(), parent.getLogCreatedAt(),
                        parent.getLogCreatedMonth(), parent.getLogCreatedYear(),
                        parent.getLogCreatedDate())
        );
    }
}
