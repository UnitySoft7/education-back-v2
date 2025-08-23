package com.school.examination.query.api.dto;

import com.school.examination.query.api.response.EstablishmentSectionClassStudentResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup establishment section class student response")
public record LookupEstablishmentSectionClassStudentResponse(
        boolean success, EstablishmentSectionClassStudentResponse establishmentSectionClassStudentResponse) implements Serializable {}
