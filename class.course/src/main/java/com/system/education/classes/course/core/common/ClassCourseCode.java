package com.system.education.classes.course.core.common;

import reactor.core.publisher.Mono;

public class ClassCourseCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            double number = Double.parseDouble(codeString.substring(4));
            return "ESCC" + String.format("%.0f", number + 1);
        });
    }
}
