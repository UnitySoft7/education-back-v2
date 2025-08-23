package com.school.press.query.api.controller;
import com.school.press.cmd.api.command.query.FindByCodeQuery;
import com.school.press.query.api.dto.AllLookupPressResponse;
import com.school.press.query.api.dto.LookupPressResponse;
import com.school.press.query.api.handler.PressQueryHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/press/lookup-press/")
@Tag(name = "Press", description = "Data REST API for class resource")
public class PressLookupController {
    private final PressQueryHandler PressQueryHandler;
    @Operation(summary = "Retrieve data Press")
    @GetMapping(path = "get-presses", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupPressResponse>> show() {
        return PressQueryHandler.findPresss().collectList().map(Press -> new AllLookupPressResponse(true, Press)).map(ResponseEntity::ok);
    }
    @Operation(summary = "Retrieve data Press by pressCode")
    @PutMapping (path = "get-press-by-press-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getPressByPressCode(@Valid @RequestBody FindByCodeQuery query) {
        return PressQueryHandler.findPressByPressCode(query.code()).map(Classroom -> new LookupPressResponse(true, Classroom)).map(ResponseEntity::ok);
    }
}
