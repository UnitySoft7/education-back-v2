package com.school.course.core.common;
import reactor.core.publisher.Mono;
public class CourseCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            if (codeString == null || !codeString.matches("^CS\\d{12}$")) {
                throw new IllegalArgumentException("Code must match format 'CS' followed by 12 digits");}
            String numberPart = codeString.substring(2);
            long number = Long.parseLong(numberPart);
            return String.format("CS%012d", number + 1);
        });
    }
}






