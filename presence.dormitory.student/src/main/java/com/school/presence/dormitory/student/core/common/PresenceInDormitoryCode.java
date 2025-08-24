package com.school.presence.dormitory.student.core.common;
import reactor.core.publisher.Mono;

public class PresenceInDormitoryCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            double number = Double.parseDouble(codeString.substring(3));
            return "PDS" + String.format("%.0f", number + 1);
        });
    }
}
