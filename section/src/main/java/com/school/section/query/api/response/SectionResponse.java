package com.school.section.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Section Response")
public record SectionResponse(
        String SectionId, String sectionName, String sectionCode,
        String frName, String enName, String establishmentCode,
        String establishmentName) implements Serializable {}
