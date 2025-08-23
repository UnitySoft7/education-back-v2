package com.school.classroom.core.common;
import reactor.core.publisher.Mono;
public class ClassroomCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            if (codeString == null || !codeString.matches("^CR\\d{12}$")) {
                throw new IllegalArgumentException("Code must match format 'CR' followed by 12 digits");
            }
            String numberPart = codeString.substring(2);
            long number = Long.parseLong(numberPart);
            return String.format("CR%012d", number + 1);
        });
    }
}