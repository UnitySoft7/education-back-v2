package com.school.dormitory.daily.query.api.dto;

import com.school.dormitory.daily.query.api.response.DormitoryStudentDailyResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup DormitoryStudentDaily Response")
public record AllLookupDormitoryStudentDailyResponse(boolean success, List<DormitoryStudentDailyResponse> DormitoryStudentDailyResponses,double effectiveDormitory,double effectiveAbscence) implements Serializable {}
