package com.system.education.student.query.api.dto;

import com.system.education.student.query.api.response.PresenceClassResponse;
import com.system.education.student.query.api.response.StudentResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup student class response")
public record LookupStudentClassResponse(
        boolean success, PresenceClassResponse presenceClassResponse) implements Serializable {}
