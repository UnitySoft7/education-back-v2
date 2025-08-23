package com.school.examination.core.common;
import reactor.core.publisher.Mono;
public class ExaminationCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            double number = Double.parseDouble(codeString.substring(3));
            return "EXN" + String.format("%.0f", number + 1);
        });
    }
}
