package com.school.presence.student.core.common;
import reactor.core.publisher.Mono;

public class PresenceInClassCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            double number = Double.parseDouble(codeString.substring(3));
            return "PIC" + String.format("%.0f", number + 1);
        });
    }
}
