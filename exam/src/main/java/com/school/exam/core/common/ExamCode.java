package com.school.exam.core.common;
import reactor.core.publisher.Mono;
public class ExamCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            double number = Double.parseDouble(codeString.substring(2));
            return "EX" + String.format("%.0f", number + 1);
        });
    }
}
