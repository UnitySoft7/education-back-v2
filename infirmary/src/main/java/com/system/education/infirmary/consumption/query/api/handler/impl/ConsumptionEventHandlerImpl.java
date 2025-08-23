package com.system.education.infirmary.consumption.query.api.handler.impl;

import com.system.education.infirmary.consumption.cmd.api.command.ConsumptionCreatedCommand;
import com.system.education.infirmary.consumption.cmd.api.command.ConsumptionPayCommand;
import com.system.education.infirmary.consumption.core.model.InfirmaryConsumption;
import com.system.education.infirmary.consumption.query.api.handler.ConsumptionEventHandler;
import com.system.education.infirmary.consumption.query.api.repository.ConsumptionRepository;
import com.system.education.infirmary.core.common.LogCreated;
import com.system.education.infirmary.core.common.Status;
import com.system.education.infirmary.core.dto.MessageResponse;
import com.system.education.infirmary.core.utils.CafeteriaUtilsConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ConsumptionEventHandlerImpl implements ConsumptionEventHandler {
    private final ConsumptionRepository consumptionRepository;

    @Override
    public Mono<MessageResponse> create(ConsumptionCreatedCommand command) {

        return consumptionRepository.existsByStudentCodeAndSemesterAndSchoolYear(command.studentCode(), command.semester(), command.schoolYear())
                .flatMap(isBoolean -> {
                    if (isBoolean) {
                        return consumptionRepository.findByStudentCodeAndSemesterAndSchoolYear(command.studentCode(), command.semester(), command.schoolYear())
                                .flatMap(infirmaryConsumption -> {
                                    infirmaryConsumption.setConsumptionAmount(infirmaryConsumption.getConsumptionAmount() + command.amount());
                                    infirmaryConsumption.setStatus(Status.not_complete());
                                    return consumptionRepository.save(infirmaryConsumption);
                                });
                    } else {
                        InfirmaryConsumption infirmaryConsumption = InfirmaryConsumption.builder()
                                .consumptionId(UUID.randomUUID().toString())
                                .studentCode(command.studentCode())
                                .studentName(command.studentName())
                                .consumptionAmount(command.amount())
                                .payedAmount(0)
                                .status(Status.not_complete())
                                .establishmentCode(command.establishmentCode())
                                .establishmentName(command.establishmentName())
                                .semester(command.semester())
                                .schoolYear(command.schoolYear())
                                .logCreatedAt(LogCreated.At())
                                .logCreatedMonth(LogCreated.Month())
                                .logCreatedYear(LogCreated.Year())
                                .logCreatedDate(LogCreated.Date())
                                .build();
                        return consumptionRepository.save(infirmaryConsumption);
                    }
            }).then(Mono.just(new MessageResponse(true, CafeteriaUtilsConstants.done)));
    }

    @Override
    public Mono<MessageResponse> pay(ConsumptionPayCommand command) {
        return consumptionRepository.findByStudentCodeAndSemesterAndSchoolYear(command.studentCode(), command.semester(), command.schoolYear())
                .flatMap(infirmaryConsumption -> {
                    double amount = infirmaryConsumption.getPayedAmount() + command.amount();
                    infirmaryConsumption.setPayedAmount(amount);
                    if (infirmaryConsumption.getConsumptionAmount() <= amount)
                        infirmaryConsumption.setStatus(Status.complete());
                    return consumptionRepository.save(infirmaryConsumption);
                }).then(Mono.just(new MessageResponse(true, CafeteriaUtilsConstants.payDone)));
    }
}
