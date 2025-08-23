package com.system.education.teacher.core.common;

import reactor.core.publisher.Mono;

public class TeacherCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            double number = Double.parseDouble(codeString.substring(4));
            return "ESTC" + String.format("%.0f", number + 1);
        });
    }
}
