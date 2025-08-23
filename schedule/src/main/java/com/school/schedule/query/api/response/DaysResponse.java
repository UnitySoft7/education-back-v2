package com.school.schedule.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Days Response")
public record DaysResponse(
        String code,String name
) implements Serializable {}
