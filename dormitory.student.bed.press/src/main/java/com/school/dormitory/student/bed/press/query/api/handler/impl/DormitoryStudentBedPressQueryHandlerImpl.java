package com.school.dormitory.student.bed.press.query.api.handler.impl;
import com.school.dormitory.student.bed.press.core.model.DormitoryStudentBedPress;
import com.school.dormitory.student.bed.press.query.api.handler.DormitoryStudentBedPressQueryHandler;
import com.school.dormitory.student.bed.press.query.api.repository.DormitoryStudentBedPressRepository;
import com.school.dormitory.student.bed.press.query.api.response.DormitoryStudentBedPressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@RequiredArgsConstructor
public class DormitoryStudentBedPressQueryHandlerImpl implements DormitoryStudentBedPressQueryHandler {
    private final DormitoryStudentBedPressRepository clothRepository;
    @Override
    public
    Flux<DormitoryStudentBedPressResponse> findDormitoryStudentBedPresss ( ) {
        return clothRepository.findAll()
                .map(this::getDormitoryStudentBedPress);
    }
    @Override
    public
    Mono<DormitoryStudentBedPressResponse> findDormitoryStudentBedPressById ( String clothId ) {
        return clothRepository.findById(clothId)
                .map(this::getDormitoryStudentBedPress);
    }
    @Override
    public
    Mono<DormitoryStudentBedPressResponse> findDormitoryStudentBedPressByDormitoryStudentBedPressCode ( String clothCode ) {
        return clothRepository.findDormitoryStudentBedPressByCode (clothCode)
                .map(this::getDormitoryStudentBedPress);
    }
    @Override
    public
    DormitoryStudentBedPressResponse getDormitoryStudentBedPress ( DormitoryStudentBedPress dormitoryStudentBedPress ) {
        return new DormitoryStudentBedPressResponse(
                dormitoryStudentBedPress.getDormitoryStudentBedPressId (),
                dormitoryStudentBedPress.getCode (),
                dormitoryStudentBedPress.getDormitoryName(),
                dormitoryStudentBedPress.getStudentFullname(),
                dormitoryStudentBedPress.getBedName(),
                dormitoryStudentBedPress.getPressName(),
                dormitoryStudentBedPress.getLogCreatedAt(),
                dormitoryStudentBedPress.getLogCreatedMonth(),
                dormitoryStudentBedPress.getLogCreatedMonth(),
                dormitoryStudentBedPress.getLogCreatedYear());
    }
}
