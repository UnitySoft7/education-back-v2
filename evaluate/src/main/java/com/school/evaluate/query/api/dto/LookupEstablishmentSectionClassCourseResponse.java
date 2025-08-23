package com.school.evaluate.query.api.dto;

import com.school.evaluate.query.api.response.EstablishmentSectionClassCourseResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup establishment section class course response")
public record LookupEstablishmentSectionClassCourseResponse(
        boolean success, EstablishmentSectionClassCourseResponse establishmentSectionClassCourseResponse) implements Serializable {}
