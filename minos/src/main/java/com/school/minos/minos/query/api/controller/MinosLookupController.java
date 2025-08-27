package com.school.minos.minos.query.api.controller;

import com.school.minos.minos.query.api.dto.AllLookupMinosResponse;
import com.school.minos.minos.query.api.dto.LookupMinosResponse;
import com.school.minos.minos.query.api.handler.MinosQueryHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/minos/minos-lookup/")
@Tag(name = "Minervale", description = "Data REST API for class resource")
public class MinosLookupController {
    private final MinosQueryHandler minosQueryHandler;
    @Operation(summary = "Retrieve data minos")
    @GetMapping(path = "get-minos", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupMinosResponse>> show() {
        return minosQueryHandler.findMinos()
                .collectList()
                .map(minos ->
                        new AllLookupMinosResponse (true, minos))
                .map(ResponseEntity::ok);
    }
    @Operation(summary = "Retrieve data minos by minosCode")
    @GetMapping(path = "get-minos-by-minos-code/{minosCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByReceiverCode(@PathVariable("minosCode") String minosCode ) {
        return minosQueryHandler.findMinosByMinosCode(minosCode)
                .map(minos ->
                        new LookupMinosResponse (true, minos))
                .map(ResponseEntity::ok);
    }


//
//
//    @Operation(summary = "Retrieve data drivers")
//    @GetMapping(path = "get-drivers", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Mono<ResponseEntity<AllLookupDriverResponse>> drivers() throws Exception {
//        return minosQueryHandler.getDrivers ()
//                .map(drivers ->
//                        new AllLookupDriverResponse (true, drivers.driverResponses() ))
//                .map(ResponseEntity::ok);
//    }

//    @Operation(summary = "Retrieve data minos by ID")
//    @GetMapping(path = "get-minos-by-minos-id/{minosId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Mono<ResponseEntity<?>> getById(@PathVariable("minosId") String minosId ) {
//        return minosQueryHandler.findMinosById(minosId)
//                .map(minos ->
//                        new LookupMinosResponse (true, minos))
//                .map(ResponseEntity::ok);
//    }

}
