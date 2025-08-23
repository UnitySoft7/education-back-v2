package com.school.dormitory.student.bed.press.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Bed Response")
public record BedResponse(
        String BedId,
        String sigle,
        String bedCode) implements Serializable {}
