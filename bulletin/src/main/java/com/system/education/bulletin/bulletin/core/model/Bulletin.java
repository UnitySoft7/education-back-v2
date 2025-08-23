package com.system.education.bulletin.bulletin.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "bulletin")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Bulletin response")
public class Bulletin {
    @Id
    private String bulletinId;
    private String bulletinCode;
    private String studentCode;
    private String studentName;
    private String dateOfBirth;
    private String daysPresent;
    private String daysAbsent;
    private String daysSick;
    private String otherReason;
    private String percent;
    private String grade;
    private String homeRoomTeacherCode;
    private String homeRoomTeacherName;
    private String homeRoomTeacherComment;
    private String academicDirectorRemark;
    private String semester;
    private String schoolYear;
    private String classCode;
    private String className;
    private String establishmentCode;
    private String establishmentName;
    private String logCreatedAt;
    private String logCreatedMonth;
    private String logCreatedYear;
    private String logCreatedDate;
}
