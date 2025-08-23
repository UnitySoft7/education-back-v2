package com.school.dormitory.student.bed.press.query.api.handler.impl;

import com.school.dormitory.student.bed.press.cmd.api.command.DormitoryStudentBedPressCreatedCommand;
import com.school.dormitory.student.bed.press.cmd.api.command.DormitoryStudentBedPressUpdatedCommand;
import com.school.dormitory.student.bed.press.core.common.LogCreated;
import com.school.dormitory.student.bed.press.core.model.DormitoryStudentBedPress;
import com.school.dormitory.student.bed.press.core.payload.DormitoryStudentBedPressPayload;
import com.school.dormitory.student.bed.press.query.api.handler.DormitoryStudentBedPressEventHandler;
import com.school.dormitory.student.bed.press.query.api.repository.DormitoryStudentBedPressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class DormitoryStudentBedPressEventHandlerImpl implements DormitoryStudentBedPressEventHandler {
    private final  DormitoryStudentBedPressRepository dormitoryStudentBedPressRepository;
    private final  DormitoryStudentBedPressPayload  dormitoryStudentBedPressPayload;
    @Override
    public
    Mono<DormitoryStudentBedPress> create ( DormitoryStudentBedPressCreatedCommand command ) {
       return dormitoryStudentBedPressPayload.getCode().flatMap(code -> {
                        DormitoryStudentBedPress value = DormitoryStudentBedPress.builder()
                                .dormitoryStudentBedPressId (UUID.randomUUID().toString())
                                .code ( code )
                                .dormitory(command.dormitory())
                                .dormitoryName(command.dormitoryName())
                                .studentFullname(command.studentFullname())
                                .student(command.student())
                                .bed(command.bed())
                                .bedName(command.bedName())
                                .press(command.press())
                                .pressName(command.pressName())
                                .logCreatedAt(LogCreated.At())
                                .logCreatedDate(LogCreated.Date())
                                .logCreatedMonth(LogCreated.Month())
                                .logCreatedYear(LogCreated.Year())
                                .build();
                        return dormitoryStudentBedPressRepository.save(value);
                    });
                }

    @Override
    public Mono<DormitoryStudentBedPress> update(DormitoryStudentBedPressUpdatedCommand command) {
        return dormitoryStudentBedPressRepository.findDormitoryStudentBedPressByCode(command.dormitoryStudentBedPressCode()).switchIfEmpty(Mono.error(new IllegalArgumentException("DormitoryStudentBedPress not found"))).flatMap(existingDormitoryStudentBedPress -> dormitoryStudentBedPressRepository.findDormitoryStudentBedPressByCode(command.dormitoryStudentBedPressCode()).filter(other -> !other.getCode().equals(existingDormitoryStudentBedPress.getCode())).flatMap(conflict -> Mono.error(new IllegalArgumentException("DormitoryStudentBedPress name already in use"))).then(Mono.defer(() -> {
            existingDormitoryStudentBedPress.setBed(command.bed());
            existingDormitoryStudentBedPress.setDormitory(command.dormitory());
            existingDormitoryStudentBedPress.setPress(command.press());
            existingDormitoryStudentBedPress.setStudent(command.student());
            return dormitoryStudentBedPressRepository.save(existingDormitoryStudentBedPress);
        })));
    }
}
