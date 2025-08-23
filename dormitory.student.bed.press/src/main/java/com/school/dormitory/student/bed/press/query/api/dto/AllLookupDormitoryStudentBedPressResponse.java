package com.school.dormitory.student.bed.press.query.api.dto;

import com.school.dormitory.student.bed.press.query.api.response.DormitoryStudentBedPressResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup DormitoryStudentBedPress Response")
public record AllLookupDormitoryStudentBedPressResponse(boolean success, List<DormitoryStudentBedPressResponse> DormitoryStudentBedPressResponses) implements Serializable {}
