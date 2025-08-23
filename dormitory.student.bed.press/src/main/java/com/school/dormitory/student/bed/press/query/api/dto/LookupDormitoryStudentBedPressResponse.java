package com.school.dormitory.student.bed.press.query.api.dto;
import com.school.dormitory.student.bed.press.query.api.response.DormitoryStudentBedPressResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
@Schema(name = "Lookup DormitoryStudentBedPress response")
public record LookupDormitoryStudentBedPressResponse(boolean success, DormitoryStudentBedPressResponse clothResponse) implements Serializable {}
