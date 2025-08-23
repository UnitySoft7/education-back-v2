package com.system.education.infirmary.consumed.query.api.handler.impl;

import com.system.education.infirmary.consumed.cmd.api.command.ConsumedCreatedCommand;
import com.system.education.infirmary.consumed.cmd.api.command.PayAllProductCommand;
import com.system.education.infirmary.consumed.cmd.api.command.ProductCommand;
import com.system.education.infirmary.consumption.cmd.api.command.ConsumptionCreatedCommand;
import com.system.education.infirmary.consumption.cmd.api.command.ConsumptionPayCommand;
import com.system.education.infirmary.consumption.query.api.handler.ConsumptionEventHandler;
import com.system.education.infirmary.core.common.LogCreated;
import com.system.education.infirmary.core.common.Status;
import com.system.education.infirmary.core.dto.MessageResponse;
import com.system.education.infirmary.consumed.core.model.InfirmaryConsumed;
import com.system.education.infirmary.core.utils.CafeteriaUtilsConstants;
import com.system.education.infirmary.consumed.query.api.handler.ConsumedEventHandler;
import com.system.education.infirmary.consumed.query.api.repository.ConsumedRepository;
import com.system.education.infirmary.diagnosis.cmd.api.command.DiagnosisCreatedCommand;
import com.system.education.infirmary.diagnosis.query.api.handler.DiagnosisEventHandler;
import com.system.education.infirmary.product.query.api.query.ProductByIdQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ConsumedEventHandlerImpl implements ConsumedEventHandler {
    private final ConsumedRepository consumedRepository;
    private final ConsumptionEventHandler consumptionEventHandler;
    private final DiagnosisEventHandler diagnosisEventHandler;

    @Override
    public Mono<MessageResponse> create(ConsumedCreatedCommand command) {
        DiagnosisCreatedCommand diagnosisCreatedCommand = new DiagnosisCreatedCommand(
                command.studentCode(), command.studentName(), command.establishmentCode(),
                command.establishmentName(), command.condition(), command.comment(),
                command.semester(), command.schoolYear());

        return diagnosisEventHandler.create(diagnosisCreatedCommand)
                .flatMap(diagnosis -> {
                    if (diagnosis != null) {
                        double consumptionAmount = 0;
                        for (ProductCommand productCommand : command.productCommands()) {
                            double amount = productCommand.amount() * Integer.parseInt(productCommand.qty());
                            consumptionAmount += amount;

                            InfirmaryConsumed infirmaryConsumed = InfirmaryConsumed.builder()
                                    .consumedId(UUID.randomUUID().toString())
                                    .productCode(productCommand.productCode())
                                    .productName(productCommand.productName())
                                    .qty(productCommand.qty())
                                    .amount(amount)
                                    .studentCode(command.studentCode())
                                    .studentName(command.studentName())
                                    .employeeCode(command.employeeCode())
                                    .employeeName(command.employeeName())
                                    .establishmentCode(command.establishmentCode())
                                    .establishmentName(command.establishmentName())
                                    .semester(command.semester())
                                    .schoolYear(command.schoolYear())
                                    .diagnosisCode(diagnosis.getDiagnosisCode())
                                    .status(Status.not_payed())
                                    .logCreatedAt(LogCreated.At())
                                    .logCreatedMonth(LogCreated.Month())
                                    .logCreatedYear(LogCreated.Year())
                                    .logCreatedDate(LogCreated.Date())
                                    .build();
                            consumedRepository.save(infirmaryConsumed).subscribe();
                        }
                        ConsumptionCreatedCommand createdCommand = new ConsumptionCreatedCommand(
                                command.studentCode(), command.studentName(), command.establishmentCode(),
                                command.establishmentName(), consumptionAmount, command.semester(), command.schoolYear());
                        return consumptionEventHandler.create(createdCommand);
                    } else {
                        return Mono.just(new MessageResponse(true, CafeteriaUtilsConstants.operation_failed));
                    }
                });

//        return Mono.just(new MessageResponse(true, CafeteriaUtilsConstants.done));
    }

    @Override
    public Mono<InfirmaryConsumed> payProduct(ProductByIdQuery command) {
        return consumedRepository.findById(command.id())
                .flatMap(infirmaryConsumed -> {
                    infirmaryConsumed.setStatus(Status.payed());

                    ConsumptionPayCommand payCommand = new ConsumptionPayCommand(infirmaryConsumed.getStudentCode(),
                            infirmaryConsumed.getAmount(), infirmaryConsumed.getSemester(), infirmaryConsumed.getSchoolYear());
                    return consumedRepository.save(infirmaryConsumed)
                            .flatMap(infirmaryConsumed1 -> consumptionEventHandler.pay(payCommand))
                            .then(Mono.just(infirmaryConsumed));
                });
    }

    @Override
    public Mono<MessageResponse> payAllProduct(PayAllProductCommand command) {
        return consumedRepository.findByStudentCodeAndSemesterAndStatusAndSchoolYear(command.studentCode(), command.semester(), Status.not_payed(), command.schoolYear())
                .collectList()
                .flatMap(consumedList -> {

                    double payedAmount = 0;
                    for (InfirmaryConsumed infirmaryConsumed : consumedList) {
                        infirmaryConsumed.setStatus(Status.payed());
                        payedAmount += infirmaryConsumed.getAmount();
                    }
                    ConsumptionPayCommand payCommand = new ConsumptionPayCommand(command.studentCode(),
                            payedAmount, command.semester(), command.schoolYear());

                    return consumedRepository.saveAll(consumedList).collectList()
                            .flatMap(consumedCollect -> consumptionEventHandler.pay(payCommand))
                            .then(Mono.just(new MessageResponse(true, CafeteriaUtilsConstants.payDone)));
                });
    }
}
