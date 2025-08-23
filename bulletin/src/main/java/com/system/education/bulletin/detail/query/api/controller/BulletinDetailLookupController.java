package com.system.education.bulletin.detail.query.api.controller;

import com.system.education.bulletin.detail.query.api.dto.AllLookupBulletinDetailResponse;
import com.system.education.bulletin.detail.query.api.dto.LookupPointResponse;
import com.system.education.bulletin.detail.query.api.dto.LookupRatingResponse;
import com.system.education.bulletin.detail.query.api.dto.LookupBulletinDetailResponse;
import com.system.education.bulletin.detail.query.api.handler.BulletinDetailQueryHandler;
import com.system.education.bulletin.detail.query.api.query.BulletinDetailByCodeAndSemesterQuery;
import com.system.education.bulletin.bulletin.query.api.query.BulletinByCodeQuery;
import com.system.education.bulletin.bulletin.query.api.query.BulletinByIdQuery;
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
@RequestMapping(path = "/api/v1/education/bulletin/bulletin-detail-lookup/")
@Tag(name = "Bulletin-detail", description = "Data REST API for bulletin-detail resource")
public class BulletinDetailLookupController {
    private final BulletinDetailQueryHandler bulletinDetailQueryHandler;

    /**
     * This method is used to retrieve all bulletin-details
     * @return the list of bulletin-details
     */
    @Operation(summary = "Retrieve data bulletin-details")
    @GetMapping(path = "get-bulletin-details", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupBulletinDetailResponse>> show() {
        return bulletinDetailQueryHandler.getBulletinDetails()
                .collectList()
                .map(bulletinDetailResponses ->
                        new AllLookupBulletinDetailResponse(true, bulletinDetailResponses))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a bulletin-detail by ID
     * @param query the ID of the bulletin-detail
     * @return the bulletin-detail with the specified ID
     */
    @Operation(summary = "Retrieve data bulletin-detail by ID")
    @PutMapping(path = "get-bulletin-detail-by-bulletin-detail-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getById(@Valid @RequestBody BulletinByIdQuery query) {
        return bulletinDetailQueryHandler.getBulletinDetailById(query)
                .map(bulletinDetailResponse ->
                        new LookupBulletinDetailResponse(true, bulletinDetailResponse))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a bulletin-detail by establishment code
     * @param query the code of the establishment
     * @return the bulletin-detail with the specified code
     */
    @Operation(summary = "Retrieve data bulletin-detail by establishment")
    @PutMapping(path = "get-bulletin-detail-by-establishment", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByEstablishment(@Valid @RequestBody BulletinByCodeQuery query) {
        return bulletinDetailQueryHandler.getBulletinDetailByEstablishment(query)
                .collectList()
                .map(bulletinDetailResponses ->
                        new AllLookupBulletinDetailResponse(true, bulletinDetailResponses))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data bulletin-detail by establishment-and-semester")
    @PutMapping(path = "get-bulletin-detail-by-establishment-and-semester", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByEstablishment(@Valid @RequestBody BulletinDetailByCodeAndSemesterQuery query) {
        return bulletinDetailQueryHandler.getBulletinDetailByEstablishmentAndSemester(query)
                .collectList()
                .map(bulletinDetailResponses ->
                        new AllLookupBulletinDetailResponse(true, bulletinDetailResponses))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data bulletin-detail by student-and-semester")
    @PutMapping(path = "get-bulletin-detail-by-student-and-semester", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByStudent(@Valid @RequestBody BulletinDetailByCodeAndSemesterQuery query) {
        return bulletinDetailQueryHandler.getBulletinDetailByStudentAndSemester(query)
                .collectList()
                .map(bulletinDetailResponses ->
                        new AllLookupBulletinDetailResponse(true, bulletinDetailResponses))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data bulletin-detail by class")
    @PutMapping(path = "get-bulletin-detail-by-class", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByClass(@Valid @RequestBody BulletinDetailByCodeAndSemesterQuery query) {
        return bulletinDetailQueryHandler.getBulletinDetailByClassAndSemester(query)
                .collectList()
                .map(bulletinDetailResponses ->
                        new AllLookupBulletinDetailResponse(true, bulletinDetailResponses))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data bulletin-detail by course")
    @PutMapping(path = "get-bulletin-detail-by-course", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByCourse(@Valid @RequestBody BulletinDetailByCodeAndSemesterQuery query) {
        return bulletinDetailQueryHandler.getBulletinDetailByCourseAndSemester(query)
                .collectList()
                .map(bulletinDetailResponses ->
                        new AllLookupBulletinDetailResponse(true, bulletinDetailResponses))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data point by student")
    @PutMapping(path = "get-point-by-student", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<LookupPointResponse>> getPointByStudent(@Valid @RequestBody BulletinDetailByCodeAndSemesterQuery query) {
        return bulletinDetailQueryHandler.getPointByStudentAndSemester(query)
                .map(pointResponse ->
                        new LookupPointResponse(true, pointResponse))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data bulletin-detail by bulletin-and-semester")
    @GetMapping(path = "get-rating-bulletin", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getRating() {
        return Mono.just(ResponseEntity.ok(new LookupRatingResponse(true, bulletinDetailQueryHandler.getRating())));

    }
}
