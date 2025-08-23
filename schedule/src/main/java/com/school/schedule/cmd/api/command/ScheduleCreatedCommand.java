package com.school.schedule.cmd.api.command;

import com.school.schedule.query.api.response.DaysResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Schedules created command")
public record ScheduleCreatedCommand(
        String index,
        String establishmentName,
        String establishmentCode,
        String sectionName,
        String sectionCode,
        String classroomName,
        String classroomCode,
        DaysResponse monday,
        DaysResponse tuesday,
        DaysResponse wednesday,
        DaysResponse thusday,
        DaysResponse friday,
        DaysResponse saturday,
        DaysResponse sunday
//        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères") String scheduleName,
//        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères") String establishmentName,
//        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères") String sectionName,
//        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères") String classroomName,
//        @NotNull(message = "Required") @Pattern(regexp = "^[0-9A-Z]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères") String classroomCode,
//        @NotNull(message = "Required") @Pattern(regexp = "^[0-9A-Z]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères") String ESC,
//        @NotNull(message = "Required") @Pattern(regexp = "^[0-9A-Z]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères") String ESCC,
//        @NotNull(message = "Required") @Pattern(regexp = "^[0-9A-Z]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères") String establishmentCode,
//        @NotNull(message = "Required") @Pattern(regexp = "^[0-9A-Z]{6,}$", message = "Ce champ doit avoir de 6 jusqu'à 40 caractères") String sectionCode,
//        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères") String dayIndex1Course,
//        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères") String dayIndex2Course,
//        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères") String dayIndex3Course,
//        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères") String dayIndex4Course,
//        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères") String dayIndex5Course,
//        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères") String dayIndex6Course,
//        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères") String dayIndex7Course,
//        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères") String dayIndex8Course,
//        @NotNull(message = "Required") @Pattern(regexp = "^[\\p{L}0-9 '-]{2,40}$", message = "Ce champ doit avoir de 2 jusqu'à 40 caractères") String day

) implements Serializable {
}
