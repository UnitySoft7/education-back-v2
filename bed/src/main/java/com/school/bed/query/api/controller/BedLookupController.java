package com.school.bed.query.api.controller;
import com.school.bed.cmd.api.command.query.FindByCodeQuery;
import com.school.bed.query.api.dto.AllLookupBedResponse;
import com.school.bed.query.api.dto.LookupBedResponse;
import com.school.bed.query.api.handler.BedQueryHandler;
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
@RequestMapping(path = "/api/v1/education/bed/lookup-bed/")
@Tag(name = "Bed", description = "Data REST API for class resource")
public class BedLookupController {
    private final BedQueryHandler BedQueryHandler;
    @Operation(summary = "Retrieve data Bed")
    @GetMapping(path = "get-beds", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupBedResponse>> show() {
        return BedQueryHandler.findBeds().collectList().map(Bed -> new AllLookupBedResponse(true, Bed)).map(ResponseEntity::ok);
    }
    @Operation(summary = "Retrieve data Bed by bedCode")
    @PutMapping(path = "get-bed-by-bed-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getBedByBedCode(@Valid @RequestBody FindByCodeQuery query) {
        return BedQueryHandler.findBedByBedCode(query.code()).map(Classroom -> new LookupBedResponse(true, Classroom)).map(ResponseEntity::ok);
    }
}
