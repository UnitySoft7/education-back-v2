package com.school.bed.query.api.handler.impl;
import com.school.bed.core.model.Bed;
import com.school.bed.query.api.handler.BedQueryHandler;
import com.school.bed.query.api.repository.BedRepository;
import com.school.bed.query.api.response.BedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@RequiredArgsConstructor
public class BedQueryHandlerImpl implements BedQueryHandler {
    private final BedRepository clothRepository;
    @Override
    public
    Flux<BedResponse> findBeds ( ) {
        return clothRepository.findAll()
                .map(this::getBed);
    }
    @Override
    public
    Mono<BedResponse> findBedById ( String clothId ) {
        return clothRepository.findById(clothId)
                .map(this::getBed);
    }
    @Override
    public
    Mono<BedResponse> findBedByBedCode ( String clothCode ) {
        return clothRepository.findBedByCode (clothCode)
                .map(this::getBed);
    }
    @Override
    public
    BedResponse getBed ( Bed bed ) {
        return new BedResponse(
                bed.getBedId (),
                bed.getSigle ( ) ,
                bed.getCode ()
        );
    }


}
