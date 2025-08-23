package com.school.press.query.api.handler.impl;
import com.school.press.core.model.Press;
import com.school.press.query.api.handler.PressQueryHandler;
import com.school.press.query.api.repository.PressRepository;
import com.school.press.query.api.response.PressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@RequiredArgsConstructor
public class PressQueryHandlerImpl implements PressQueryHandler {
    private final PressRepository clothRepository;
    @Override
    public
    Flux<PressResponse> findPresss ( ) {
        return clothRepository.findAll()
                .map(this::getPress);
    }
    @Override
    public
    Mono<PressResponse> findPressById ( String clothId ) {
        return clothRepository.findById(clothId)
                .map(this::getPress);
    }
    @Override
    public
    Mono<PressResponse> findPressByPressCode ( String clothCode ) {
        return clothRepository.findPressByCode (clothCode)
                .map(this::getPress);
    }
    @Override
    public
    PressResponse getPress ( Press press ) {
        return new PressResponse(
                press.getPressId (),
                press.getSigle ( ) ,
                press.getCode ()
        );
    }


}
