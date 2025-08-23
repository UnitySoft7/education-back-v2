package com.school.evaluate.query.api.handler.impl;

import com.school.evaluate.cmd.api.query.FindByEstablishClassSectionProfCourseTrimesterYearQuery;
import com.school.evaluate.cmd.api.query.FindByStudentCourseTrimesterYearQuery;
import com.school.evaluate.cmd.api.query.FindByTrimesterQuery;
import com.school.evaluate.cmd.api.query.FindByYearQuery;
import com.school.evaluate.core.model.Evaluate;
import com.school.evaluate.query.api.handler.EvaluateQueryHandler;
import com.school.evaluate.query.api.repository.EvaluateRepository;
import com.school.evaluate.query.api.response.EvaluateMaxResponse;
import com.school.evaluate.query.api.response.EvaluateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EvaluateQueryHandlerImpl implements EvaluateQueryHandler {
    private final EvaluateRepository evaluateRepository;



    @Override
    public Flux<EvaluateResponse> findAllEvaluates() {
        return evaluateRepository.findAll().map(this::getEvaluate);
    }

    @Override
    public Flux<EvaluateResponse> findAllEvaluateByStudent(String query) {
        return evaluateRepository.findAllEvaluateByStudentCode(query).map(this::getEvaluate);
    }

    @Override
    public Flux<EvaluateResponse> findAllEvaluateByTrimester(FindByTrimesterQuery query) {
        return evaluateRepository.findAllEvaluateByTrimester(query.trimester()).map(this::getEvaluate);
    }

    @Override
    public Flux<EvaluateResponse> findAllEvaluateByEstaClassSectionProfSemeYear(FindByEstablishClassSectionProfCourseTrimesterYearQuery query) {
        return evaluateRepository.
                findAllEvaluateByEstablishmentCodeAndClassroomCodeAndSectionCodeAndProfCodeAndCourseCodeAndTrimesterAndSchoolYear(
                        query.establishmentCode(),
                        query.classCode(),
                        query.sectionCode(),
                        query.profCode(),
                        query.coursecode(),
                        query.semestre(),
                        query.year()
                ).map(this::getEvaluate);
    }

    @Override
    public Flux<EvaluateResponse> findAllEvaluateByYear(FindByYearQuery query) {
        return evaluateRepository.findAllEvaluateBySchoolYear(query.year()).map(this::getEvaluate);
    }

    @Override
    public Flux<EvaluateMaxResponse> findMaxEvaluateByCode(String studentCode) {
        return evaluateRepository.sumNotesByEscs(studentCode)
                .map(this::getMaxEvaluate);
    }
    @Override
    public Mono<Double> findSumNotesByCode(String studentCode) {
        return evaluateRepository.sumNotesByEscs(studentCode)  // Flux<Evaluate>
                .map(Evaluate::getNote)
                .reduce(0.0, Double::sum);            // utiliser Double::sum et valeur initiale 0.0
    }
    @Override
    public Mono<EvaluateResponse> findEvaluateByCode(String code) {
        return evaluateRepository.findEvaluateByCode(code).map(this::getEvaluate);
    }

    @Override
    public Mono<EvaluateResponse> findAllExaminationsBySCSY(FindByStudentCourseTrimesterYearQuery query) {
        return evaluateRepository.findEvaluateByStudentCodeAndCourseCodeAndTrimesterAndSchoolYear(
                query.studentCode(),
                query.coursecode(),
                query.semestre(),
                query.year()
        ).map(this::getEvaluate);
    }

    @Override
    public EvaluateMaxResponse getMaxEvaluate(Evaluate evaluate) {
        return new EvaluateMaxResponse(
//                evaluate.getstudentCode(),
                evaluate.getNote(), evaluate.getNoteMax() // ici la somme`
        );
    }


    @Override
    public EvaluateResponse getEvaluate(Evaluate evaluate) {
        return new EvaluateResponse(evaluate.getEvaluateId(), evaluate.getCode(),
                evaluate.getEvaluationName(), evaluate.getEvaluationCode(),
                evaluate.getProfFullname(), evaluate.getProfCode(),
                evaluate.getCourseName(), evaluate.getCourseCode(),
                evaluate.getStudentCode(), evaluate.getStudentFullname(),
                120, evaluate.getNoteMax(), evaluate.getNote(),
                evaluate.getEstablishmentName(), evaluate.getEstablishmentCode(),evaluate.getSectionCode(),
                evaluate.getSectionName(),
                evaluate.getClassroomName(), evaluate.getClassroomCode(),
                evaluate.getTrimester(), evaluate.getSchoolYear(),evaluate.getComment());
    }


}
