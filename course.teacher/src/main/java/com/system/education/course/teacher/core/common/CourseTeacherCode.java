package com.system.education.course.teacher.core.common;

import reactor.core.publisher.Mono;

public class CourseTeacherCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            double number = Double.parseDouble(codeString.substring(5));
            return "ESCTC" + String.format("%.0f", number + 1);
        });
    }
}
