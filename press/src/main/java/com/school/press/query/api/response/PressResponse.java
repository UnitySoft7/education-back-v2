package com.school.press.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Press Response")
public record PressResponse(
        String pressId,
        String sigle,
        String pressCode) implements Serializable {}
