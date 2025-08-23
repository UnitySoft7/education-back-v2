package com.system.education.cafeteria.consumed.query.api.handler.impl;

import com.system.education.cafeteria.consumed.cmd.api.command.ConsumedCreatedCommand;
import com.system.education.cafeteria.consumed.cmd.api.command.PayAllProductCommand;
import com.system.education.cafeteria.consumed.cmd.api.command.ProductCommand;
import com.system.education.cafeteria.consumption.cmd.api.command.ConsumptionCreatedCommand;
import com.system.education.cafeteria.consumption.cmd.api.command.ConsumptionPayCommand;
import com.system.education.cafeteria.consumption.query.api.handler.ConsumptionEventHandler;
import com.system.education.cafeteria.consumption.query.api.repository.ConsumptionRepository;
import com.system.education.cafeteria.core.common.LogCreated;
import com.system.education.cafeteria.core.common.Status;
import com.system.education.cafeteria.core.dto.MessageResponse;
import com.system.education.cafeteria.consumed.core.model.Consumed;
import com.system.education.cafeteria.core.utils.CafeteriaUtilsConstants;
import com.system.education.cafeteria.consumed.query.api.handler.ConsumedEventHandler;
import com.system.education.cafeteria.consumed.query.api.repository.ConsumedRepository;
import com.system.education.cafeteria.product.query.api.query.ProductByIdQuery;
import com.system.education.cafeteria.product.query.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ConsumedEventHandlerImpl implements ConsumedEventHandler {
    private final ConsumedRepository consumedRepository;
    private final ConsumptionEventHandler consumptionEventHandler;

    @Override
    public Mono<MessageResponse> create(ConsumedCreatedCommand command) {
        double consumptionAmount = 0;
        for (ProductCommand productCommand : command.productCommands()) {
                double amount = productCommand.amount() * Integer.parseInt(productCommand.qty());
                consumptionAmount += amount;

                Consumed consumed = Consumed.builder()
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
                        .status(Status.not_payed())
                        .logCreatedAt(LogCreated.At())
                        .logCreatedMonth(LogCreated.Month())
                        .logCreatedYear(LogCreated.Year())
                        .logCreatedDate(LogCreated.Date())
                        .build();
               consumedRepository.save(consumed).subscribe();
        }
        ConsumptionCreatedCommand createdCommand = new ConsumptionCreatedCommand(
                command.studentCode(), command.studentName(), command.establishmentCode(),
                command.establishmentName(), consumptionAmount, command.semester(), command.schoolYear());
        return consumptionEventHandler.create(createdCommand);
//        return Mono.just(new MessageResponse(true, CafeteriaUtilsConstants.done));
    }

    @Override
    public Mono<Consumed> payProduct(ProductByIdQuery command) {
        return consumedRepository.findById(command.id())
                .flatMap(consumed -> {
                    consumed.setStatus(Status.payed());

                    ConsumptionPayCommand payCommand = new ConsumptionPayCommand(consumed.getStudentCode(),
                            consumed.getAmount(), consumed.getSemester(), consumed.getSchoolYear());
                    return consumedRepository.save(consumed)
                            .flatMap(consumed1 -> consumptionEventHandler.pay(payCommand))
                            .then(Mono.just(consumed));
                });
    }

    @Override
    public Mono<MessageResponse> payAllProduct(PayAllProductCommand command) {
        return consumedRepository.findByStudentCodeAndSemesterAndStatusAndSchoolYear(command.studentCode(), command.semester(), Status.not_payed(), command.schoolYear())
                .collectList()
                .flatMap(consumedList -> {

                    double payedAmount = 0;
                    for (Consumed consumed: consumedList) {
                        consumed.setStatus(Status.payed());
                        payedAmount += consumed.getAmount();
                    }
                    ConsumptionPayCommand payCommand = new ConsumptionPayCommand(command.studentCode(),
                            payedAmount, command.semester(), command.schoolYear());

                    return consumedRepository.saveAll(consumedList).collectList()
                            .flatMap(consumedCollect -> consumptionEventHandler.pay(payCommand))
                            .then(Mono.just(new MessageResponse(true, CafeteriaUtilsConstants.payDone)));
                });
    }
}
