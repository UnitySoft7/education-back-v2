package com.school.minos.query.api.handler.impl;

import com.school.minos.cmd.api.command.MinosCreatedCommand;
import com.school.minos.cmd.api.command.MinosPayedCommand;
import com.school.minos.cmd.api.command.MinosUpdatedCommand;
import com.school.minos.core.common.LogCreated;
import com.school.minos.core.common.Status;
import com.school.minos.core.model.Minos;
import com.school.minos.transaction.core.model.Transaction;
import com.school.minos.core.payload.MinosPayload;
import com.school.minos.transaction.core.payload.TransactionPayload;
import com.school.minos.query.api.handler.MinosEventHandler;
import com.school.minos.query.api.repository.MinosRepository;
import com.school.minos.transaction.query.api.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MinosEventHandlerImpl implements MinosEventHandler {
    private final MinosRepository minosRepository;
    private final MinosPayload minosPayload;
    private final TransactionPayload transactionPayload;
    private final TransactionRepository transactionRepository;

    @Override
    public Mono<Minos> create(MinosCreatedCommand command) {
        return minosPayload.getCode().flatMap(code -> {
            Minos value = Minos.builder()
                    .minosId(UUID.randomUUID().toString())
                    .minosCode(code)
                    .studentCode(command.studentCode())
                    .studentFullname(command.studentName())
                    .semester(command.semester())
                    .amount(0)
                    .maxamount(command.maxamount())
                    .status(Status.disable())
                    .schoolYear(command.schoolYear())
                    .establishmentName(command.establishmentName())
                    .establishmentCode(command.establishmentCode())
                    .sectionName(command.sectionName())
                    .sectionCode(command.studentCode())
                    .classroomName(command.className())
                    .classroomCode(command.classCode())
                    .build();
            return minosRepository.save(value);
        });
    }

    @Override
    public Mono<Transaction> pay(MinosPayedCommand command) {
        return transactionPayload.getCode()
                .flatMap(code ->
                        minosRepository.findMinosByMinosCode(command.minosCode())
                                .switchIfEmpty(Mono.error(new RuntimeException("Minos not found for code: " + command.minosCode())))
                                .flatMap(minos -> {
                                    // Sum of amounts already paid for this ESCS
                                    return transactionPayload.sumAmountByESCS(command.ESCS())
                                            .flatMap(sumPaid -> {
                                                double maxAmount = minos.getMaxamount();//max
                                                double totalAfterPayment = sumPaid + command.amount();//all paid
                                                double paid= (command.amount()-maxAmount);

                                                if (totalAfterPayment > maxAmount) {
                                                    double overpaid = totalAfterPayment - maxAmount;
                                                    double allowedRemaining = maxAmount-sumPaid  ;
//                                                    double toPay=

                                                    return Mono.error(new RuntimeException(
                                                            "Tu depasses : " + paid + " FBU au minervale annuel. Tu as paye : " + sumPaid + " FBU " +
                                                                    "pour cette année "+"Et il te rester seulement" +allowedRemaining+" over: "+overpaid+" max: "+maxAmount+" tot: "+totalAfterPayment+" Somme payee "+sumPaid
//                                                                    +maxAmount-totalAfterPayment)
                                                    ));
                                                }
                                                // Build and save the transaction with the payment
                                                Transaction transaction = Transaction.builder()
                                                        .transaId(UUID.randomUUID().toString())
                                                        .transaCode(code)
                                                        .minosCode(minos.getMinosCode())
                                                        .studentCode(command.ESCS())
                                                        .establishmentCode(minos.getEstablishmentCode())
                                                        .establishmentName(minos.getEstablishmentName())
                                                        .amount(totalAfterPayment)  // This payment amount
                                                        .maxamount(maxAmount)      // Max allowed amount
                                                        .studentFullname(minos.getStudentFullname())
                                                        .status(Status.enable())
                                                        .sectionName(minos.getSectionName())
                                                        .sectionCode(minos.getSectionCode())
                                                        .classroomCode(minos.getClassroomCode())
                                                        .classroomName(minos.getClassroomName())
                                                        .trimester(command.trimester())
                                                        .logCreatedAt(LogCreated.At())
                                                        .logCreatedDate(LogCreated.Date())
                                                        .logCreatedMonth(LogCreated.Month())
                                                        .logCreatedYear(LogCreated.Year())
                                                        .build();

                                                return transactionRepository.save(transaction);
                                            });
                                })
                );
    }



    @Override
    public Mono<Minos> update(MinosUpdatedCommand command) {
        return minosRepository.findMinosByMinosCode(command.minosCode()).flatMap(minos -> {
            minos.setAmount(command.amount());
            minos.setSemester(command.trimester());
            minos.setSchoolYear(command.schoolYear());
            return minosRepository.save(minos);
        });
    }

}
