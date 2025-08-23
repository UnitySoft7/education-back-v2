package com.system.education.student.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.codec.multipart.FilePart;

import java.io.Serializable;

@Schema(name = "Student created command")
public record StudentCreatedCommand(
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[\\p{L} '-]{2,}$", message = "The full name must have at least 2 characters")
        String fullName,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z0-9-/.]{1,20}$", message = "")
        String birthday,
        @NotNull(message = "The field is required")
        @Pattern(regexp = "^[A-Z]{3,5}", message = "The gender must be between 3 and 5 characters")
        String gender,
        @NotNull(message = "The field is required")
        String address,
        @Pattern(regexp = "^[\\p{L} '-]*$", message = "Le nom du father doit avoir au moins 2 caractères")
        String fatherName,
        @Pattern(regexp = "^[+0-9 ]*", message = "Invalid father mobile number")
        String fatherMobileNo,
        @Pattern(regexp = "^[\\p{L} '-]*$", message = "Le nom du mother doit avoir au moins 2 caractères")
        String motherName,
        @Pattern(regexp = "^[+0-9 ]*", message = "Invalid mother mobile number")
        String motherMobileNo,
        FilePart profile,
        FilePart extract,
        FilePart bulletin,
        String schoolYear,
        String establishmentCode,
        String establishmentName,
        String sectionCode,
        String sectionName,
        String classCode,
        String className) implements Serializable {}
