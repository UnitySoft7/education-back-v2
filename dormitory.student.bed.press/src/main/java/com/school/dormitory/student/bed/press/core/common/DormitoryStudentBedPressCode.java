package com.school.dormitory.student.bed.press.core.common;
import reactor.core.publisher.Mono;
public class DormitoryStudentBedPressCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            if (codeString == null || !codeString.matches("^DSBP\\d{12}$")) {
                throw new IllegalArgumentException("Code must match format 'DSBP' followed by 6 digits");}
            String numberPart = codeString.substring(4);
            long number = Long.parseLong(numberPart);
            return String.format("DSBP%12d", number + 1);
        });
    }
}






