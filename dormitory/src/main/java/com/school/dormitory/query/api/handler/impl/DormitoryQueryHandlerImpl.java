package com.school.dormitory.query.api.handler.impl;
import com.school.dormitory.core.model.Dormitory;
import com.school.dormitory.query.api.handler.DormitoryQueryHandler;
import com.school.dormitory.query.api.repository.DormitoryRepository;
import com.school.dormitory.query.api.response.DormitoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@RequiredArgsConstructor
public class DormitoryQueryHandlerImpl implements DormitoryQueryHandler {
    private final DormitoryRepository clothRepository;
    @Override
    public
    Flux<DormitoryResponse> findDormitorys ( ) {
        return clothRepository.findAll()
                .map(this::getDormitory);
    }
    @Override
    public
    Mono<DormitoryResponse> findDormitoryById ( String clothId ) {
        return clothRepository.findById(clothId)
                .map(this::getDormitory);
    }
    @Override
    public
    Mono<DormitoryResponse> findDormitoryByDormitoryCode ( String clothCode ) {
        return clothRepository.findDormitoryByCode (clothCode)
                .map(this::getDormitory);
    }
    @Override
    public
    DormitoryResponse getDormitory ( Dormitory dormitory ) {
        return new DormitoryResponse(
                dormitory.getDormitoryId (),
                dormitory.getName ( ) ,
                dormitory.getCode (),dormitory.getCategory(),dormitory.getSchoolYear(),dormitory.getLogCreatedAt(), dormitory.getLogCreatedMonth(), dormitory.getLogCreatedMonth(), dormitory.getLogCreatedYear());
    }
}
