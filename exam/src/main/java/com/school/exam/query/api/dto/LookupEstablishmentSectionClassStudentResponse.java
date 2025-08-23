package com.school.exam.query.api.dto;

import com.school.exam.query.api.response.EstablishmentSectionClassStudentResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup establishment section class student response")
public record LookupEstablishmentSectionClassStudentResponse(
        boolean success, EstablishmentSectionClassStudentResponse establishmentSectionClassStudentResponse) implements Serializable {}
