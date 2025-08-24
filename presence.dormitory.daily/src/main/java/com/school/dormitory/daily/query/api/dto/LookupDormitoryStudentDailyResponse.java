package com.school.dormitory.daily.query.api.dto;
import com.school.dormitory.daily.query.api.response.DormitoryStudentDailyResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
@Schema(name = "Lookup DormitoryStudentDaily response")
public record LookupDormitoryStudentDailyResponse(boolean success, DormitoryStudentDailyResponse clothResponse) implements Serializable {}
