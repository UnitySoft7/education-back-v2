package com.school.dormitory.query.api.controller;
import com.school.dormitory.cmd.api.command.query.FindByCodeQuery;
import com.school.dormitory.core.common.Category;
import com.school.dormitory.query.api.dto.AllLookupCategoriesResponse;
import com.school.dormitory.query.api.dto.AllLookupDormitoryResponse;
import com.school.dormitory.query.api.dto.LookupDormitoryResponse;
import com.school.dormitory.query.api.handler.DormitoryQueryHandler;
import com.school.dormitory.query.api.response.CategoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/dormitory/lookup-dormitory/")
@Tag(name = "Dormitory", description = "Data REST API for class resource")
public class DormitoryLookupController {
    private final DormitoryQueryHandler DormitoryQueryHandler;
    @Operation(summary = "Retrieve data Dormitory")
    @GetMapping(path = "get-dormitories", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupDormitoryResponse>> show() {
        return DormitoryQueryHandler.findDormitorys()
                .collectList()
                .map(Dormitory ->
                        new AllLookupDormitoryResponse (true, Dormitory))
                .map(ResponseEntity::ok);
    }
    @Operation(summary = "Retrieve data Dormitory by dormitoryCode")
    @PutMapping(path = "get-dormitory-by-dormitory-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getDormitoryByDormitoryCode(@Valid @RequestBody FindByCodeQuery query) {
        return DormitoryQueryHandler.findDormitoryByDormitoryCode (query.code())
                .map(dormitory ->
                        new LookupDormitoryResponse (true, dormitory))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data categories")
    @GetMapping(path = "/get-category", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupCategoriesResponse>> getAllCategories() {
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        categoryResponses.add(Category.masculine());
        categoryResponses.add(Category.feminine());

        AllLookupCategoriesResponse response = new AllLookupCategoriesResponse(true, categoryResponses);
        return Mono.just(ResponseEntity.ok(response));
    }

}
