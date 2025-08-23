package com.school.examination.query.api.handler.impl;

import com.school.examination.cmd.api.query.FindByEstablishClassSectionProfCourseTrimesterYearQuery;
import com.school.examination.cmd.api.query.FindByStudentCourseTrimesterYearQuery;
import com.school.examination.cmd.api.query.FindByTrimesterQuery;
import com.school.examination.cmd.api.query.FindByYearQuery;
import com.school.examination.core.model.Examination;
import com.school.examination.query.api.handler.ExaminationQueryHandler;
import com.school.examination.query.api.repository.ExaminationRepository;
import com.school.examination.query.api.response.ExaminationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExaminationQueryHandlerImpl implements ExaminationQueryHandler {
    private final ExaminationRepository examinationRepository;

    @Override
    public Flux<ExaminationResponse> findExaminations() {
        return examinationRepository.findAll().map(this::getExamination);
    }
//    @Override
//    public Flux<ExaminationResponse> findAllExaminationsByESCC(FindByCodeQuery query) {
//        return examinationRepository.findAllExaminationByESCC(query.code()).map(this::getExamination);
//    }
//    @Override
//    public Flux<ExaminationResponse> findAllExaminationsByESCS(FindByCodeQuery query) {
//        return examinationRepository.findAllExaminationByESCS(query.code()).map(this::getExamination);
//    }
//    @Override
//    public Flux<ExaminationResponse> findAllExaminationsByESCCT(FindByCodeQuery query) {
//        return examinationRepository.findAllExaminationByESCCT(query.code()).map(this::getExamination);
//    }
    @Override
    public Flux<ExaminationResponse> findAllExaminationsByTrimester(FindByTrimesterQuery query) {
        return examinationRepository.findAllExaminationByTrimester(query.trimester()).map(this::getExamination);
    }

    @Override
    public Flux<ExaminationResponse> findAllExaminationsByYear(FindByYearQuery query) {
        return examinationRepository.findAllExaminationBySchoolYear(query.year()).map(this::getExamination);
    }
    @Override
    public Flux<ExaminationResponse> findAllExaminationsByECSP(FindByEstablishClassSectionProfCourseTrimesterYearQuery query) {
        return examinationRepository.findExaminationByEstablishmentCodeAndClassroomCodeAndSectionCodeAndProfCodeAndCourseCodeAndTrimesterAndSchoolYear(
                query.establishmentCode(), query.classCode(), query.sectionCode(), query.profCode(), query.coursecode(), query.semestre(), query.year()).map(this::getExamination);
    }
    @Override
    public Mono<ExaminationResponse> findAllExaminationsBySCSY(FindByStudentCourseTrimesterYearQuery query) {
        return examinationRepository.findExaminationByStudentCodeAndCourseCodeAndTrimesterAndSchoolYear(
                query.studentCode(),
                query.coursecode(),
                query.semestre(),
                query.year()
        ).map(this::getExamination);
    }

    @Override
    public Mono<ExaminationResponse> findExaminationByExaminationCode(String code) {
        return examinationRepository.findExaminationByCode(code).map(this::getExamination);
    }

    @Override
    public ExaminationResponse getExamination(Examination examination) {
        double note= examination.getNote();
        double noteMax= examination.getNoteMax();
        double pnder= 120;
        double maxPond=(note/noteMax)*pnder;
        double pour =(maxPond/pnder)*100;
        return new ExaminationResponse(examination.getExaminationId(),
                examination.getCode(), examination.getExamName(), examination.getExamCode(),
                examination.getCourseName(), examination.getCourseCode(),
                examination.getStudentCode(),
                examination.getStudentFullname(), examination.getProfFullname(),
                examination.getProfCode(), examination.getNoteMax(),
                examination.getNote(),maxPond,pour,
//                examination.getESCC(),       examination.getESCCT(),examination.getESCS(),
                examination.getEstablishmentName(),
                examination.getEstablishmentCode(), examination.getSectionName(),
                examination.getSectionCode(), examination.getClassroomName(),
                examination.getClassroomCode(), examination.getTrimester(),
                examination.getSchoolYear(),examination.getComment());
    }


}
