package com.system.education.teacher.core.payload;

import com.system.education.teacher.core.dto.MessageResponse;
import reactor.core.publisher.Mono;

public interface TeacherPayload {
    Mono<String> getCode();

    Mono<Boolean> isTeacherCodeExist(String teacherCode);

    Mono<MessageResponse> verifyPassword(String newPassword, String verifyPassword);
}
