package com.school.evaluate.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Evaluate Max Response")
public record EvaluateMaxResponse(
//        String ESCS,
        double note,
        double max
) implements Serializable {}
