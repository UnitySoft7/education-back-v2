package com.school.schedule.core.common;
import reactor.core.publisher.Mono;
public class ScheduleCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            double number = Double.parseDouble(codeString.substring(2));
            return "SH" + String.format("%.0f", number + 1);
        });
    }
}
