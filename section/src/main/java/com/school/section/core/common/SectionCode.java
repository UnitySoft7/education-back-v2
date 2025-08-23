package com.school.section.core.common;
import reactor.core.publisher.Mono;

public class SectionCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            double number = Double.parseDouble(codeString.substring(2));
            return "SN" + String.format("%.0f", number + 1);
        });
    }
}
