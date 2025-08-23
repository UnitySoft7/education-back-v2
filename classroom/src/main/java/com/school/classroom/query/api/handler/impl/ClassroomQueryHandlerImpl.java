package com.school.classroom.query.api.handler.impl;
import com.school.classroom.core.model.Classroom;
import com.school.classroom.query.api.handler.ClassroomQueryHandler;
import com.school.classroom.query.api.repository.ClassroomRepository;
import com.school.classroom.query.api.response.ClassroomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@RequiredArgsConstructor
public class ClassroomQueryHandlerImpl implements ClassroomQueryHandler {
    private final ClassroomRepository classroomRepository;
    @Override
    public
    Flux<ClassroomResponse> findClassrooms() {
        return classroomRepository.findAll()
                .map(this::getClassroom);
    }
    @Override
    public
    Mono<ClassroomResponse> findClassroomById(String clothId) {
        return classroomRepository.findById(clothId)
                .map(this::getClassroom);
    }
    @Override
    public
    Mono<ClassroomResponse> findClassroomByClassroomCode(String clothCode) {
        return classroomRepository.findClassroomByCode (clothCode)
                .map(this::getClassroom);
    }
    @Override
    public
    Flux<ClassroomResponse> findByEstablishmentCode(String establishmentCode) {
        return classroomRepository.findByEstablishmentCode (establishmentCode)
                .map(this::getClassroom);
    }
    @Override
    public
    ClassroomResponse getClassroom(Classroom classroom) {
        return new ClassroomResponse(
                classroom.getClassroomId (),
                classroom.getName ( ) ,
                classroom.getCode (),
                classroom.getFrName (),
                classroom.getEnName (),
                classroom.getSectionCode (),
                classroom.getSectionName (),
                classroom.getEstablishmentCode (),
                classroom.getEstablishmentName ()
        );
    }
}
