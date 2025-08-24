package com.school.dormitory.daily.core.common;
import reactor.core.publisher.Mono;
public class DormitoryStudentDailyCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            if (codeString == null || !codeString.matches("^PDC\\d{12}$")) {
                throw new IllegalArgumentException("Code must match format 'PDC' followed by 12 digits");}
            String numberPart = codeString.substring(3);
            long number = Long.parseLong(numberPart);
            return String.format("PDC%012d", number + 1);
        });
    }
}






