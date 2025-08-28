package com.school.minos.minos.query.api.handler.impl;

import com.school.minos.minos.cmd.api.command.MinosPayedCommand;
import com.school.minos.core.common.LogCreated;
import com.school.minos.minos.core.model.Minos;
import com.school.minos.tariff.query.api.repository.TariffRepository;
import com.school.minos.transaction.core.model.Transaction;
import com.school.minos.minos.core.payload.MinosPayload;
import com.school.minos.minos.query.api.handler.MinosEventHandler;
import com.school.minos.minos.query.api.repository.MinosRepository;
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
    private final TransactionRepository transactionRepository;
    private final TariffRepository tariffRepository;

    @Override
    public Mono<Transaction> pay(MinosPayedCommand command) {
        return tariffRepository.findTariffByEstablishmentCodeAndSectionCodeAndClassroomCode(
                command.establishmentCode(), command.sectionCode(), command.classroomCode())
                        .flatMap(tariff -> minosRepository.existsByStudentCodeAndSemesterAndSchoolYear(
                                command.studentCode(), command.trimester(), command.schoolYear())
                                .flatMap(exists -> {
                                    if (exists) {
                                        return minosRepository.findByStudentCodeAndSemesterAndSchoolYear(
                                                command.studentCode(), command.trimester(), command.schoolYear())
                                                .flatMap(minos -> {
                                                    double maxAmount = minos.getMaxamount();//max
                                                    double totalAfterPayment = minos.getAmount() + command.amount();

                                                    if (totalAfterPayment > maxAmount) {
                                                        double overpaid = totalAfterPayment - maxAmount;
                                                        double allowedRemaining = maxAmount-minos.getAmount()  ;

                                                        return Mono.error(new RuntimeException(
                                                                "Tu depasses : " + overpaid + " FBU au minervale annuel. Tu as paye : "
                                                                        + minos.getAmount() + " FBU " + "pour cette semestre, "+
                                                                        "et il te rester seulement " +allowedRemaining+ " over: " +
                                                                        overpaid+" max: "+maxAmount+" tot: "+totalAfterPayment+
                                                                        " Somme payee "+ minos.getAmount()
                                                        ));
                                                    }
                                                    minos.setAmount(totalAfterPayment);
                                                    minos.setLogModifiedAt(LogCreated.At());
                                                    return minosRepository.save(minos).flatMap(minos1 -> {
                                                        Transaction transaction = Transaction.builder()
                                                                .transaId(UUID.randomUUID().toString())
                                                                .minosCode(minos.getMinosCode())
                                                                .studentCode(command.studentCode())
                                                                .studentFullname(command.studentName())
                                                                .trimester(command.trimester())
                                                                .amount(totalAfterPayment)
                                                                .maxamount(maxAmount)
                                                                .schoolYear(command.schoolYear())
                                                                .establishmentCode(command.establishmentCode())
                                                                .establishmentName(command.establishmentName())
                                                                .sectionName(command.sectionName())
                                                                .sectionCode(command.sectionCode())
                                                                .classroomCode(command.classroomCode())
                                                                .classroomName(command.classroomName())
                                                                .logCreatedAt(LogCreated.At())
                                                                .logCreatedDate(LogCreated.Date())
                                                                .logCreatedMonth(LogCreated.Month())
                                                                .logCreatedYear(LogCreated.Year())
                                                                .build();
                                                        return transactionRepository.save(transaction);
                                                    });
                                                });
                                    } else {
                                        return minosPayload.getCode().flatMap(code -> {
                                            double maxAmount = tariff.getAmount();
                                            double totalAfterPayment = command.amount();

                                            if (totalAfterPayment > maxAmount) {
                                                double overpaid = totalAfterPayment - maxAmount;

                                                return Mono.error(new RuntimeException(
                                                        "Tu depasses : " + overpaid + " FBU au minervale semetriel. Tu as paye : "
                                                                + 0 + " FBU " + "pour cette semestre, "+"et il te rester seulement "
                                                                + maxAmount + " over: "+ overpaid+" max: "+maxAmount+" tot: "
                                                                +totalAfterPayment+" Somme payee "+ 0
                                                ));
                                            }
                                            Minos minos = Minos.builder()
                                                    .minosId(UUID.randomUUID().toString())
                                                    .minosCode(code)
                                                    .studentCode(command.studentCode())
                                                    .studentFullname(command.studentName())
                                                    .semester(command.trimester())
                                                    .amount(command.amount())
                                                    .maxamount(tariff.getAmount())
                                                    .status("")
                                                    .schoolYear(command.schoolYear())
                                                    .establishmentName(command.establishmentName())
                                                    .establishmentCode(command.establishmentCode())
                                                    .sectionName(command.sectionName())
                                                    .sectionCode(command.sectionCode())
                                                    .classroomName(command.classroomName())
                                                    .classroomCode(command.classroomCode())
                                                    .logCreatedAt(LogCreated.At())
                                                    .logCreatedDate(LogCreated.Date())
                                                    .logCreatedMonth(LogCreated.Month())
                                                    .logCreatedYear(LogCreated.Year())
                                                    .build();
                                            return minosRepository.save(minos).flatMap(minos1 -> {
                                                Transaction transaction = Transaction.builder()
                                                        .transaId(UUID.randomUUID().toString())
                                                        .minosCode(minos.getMinosCode())
                                                        .studentCode(command.studentCode())
                                                        .studentFullname(command.studentName())
                                                        .trimester(command.trimester())
                                                        .amount(minos1.getAmount())
                                                        .maxamount(minos1.getMaxamount())
                                                        .schoolYear(command.schoolYear())
                                                        .establishmentCode(command.establishmentCode())
                                                        .establishmentName(command.establishmentName())
                                                        .sectionName(command.sectionName())
                                                        .sectionCode(command.sectionCode())
                                                        .classroomCode(command.classroomCode())
                                                        .classroomName(command.classroomName())
                                                        .logCreatedAt(LogCreated.At())
                                                        .logCreatedDate(LogCreated.Date())
                                                        .logCreatedMonth(LogCreated.Month())
                                                        .logCreatedYear(LogCreated.Year())
                                                        .build();

                                                return transactionRepository.save(transaction);
                                            });
                                        });

                                    }
                                })
                        );
    }
}
