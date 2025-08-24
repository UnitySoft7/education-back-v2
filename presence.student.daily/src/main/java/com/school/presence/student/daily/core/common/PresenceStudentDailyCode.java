package com.school.presence.student.daily.core.common;
import reactor.core.publisher.Mono;
public class PresenceStudentDailyCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            if (codeString == null || !codeString.matches("^PSD\\d{12}$")) {
                throw new IllegalArgumentException("Code must match format 'PSD' followed by 12 digits");}
            String numberPart = codeString.substring(3);
            long number = Long.parseLong(numberPart);
            return String.format("PSD%012d", number + 1);
        });
    }
}






