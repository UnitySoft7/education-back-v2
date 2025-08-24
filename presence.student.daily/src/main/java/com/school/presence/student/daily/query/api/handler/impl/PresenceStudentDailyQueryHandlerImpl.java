package com.school.presence.student.daily.query.api.handler.impl;
import com.school.presence.student.daily.cmd.api.command.query.FindByCodeQuery;
import com.school.presence.student.daily.core.model.PresenceStudentDaily;
import com.school.presence.student.daily.query.api.handler.PresenceStudentDailyQueryHandler;
import com.school.presence.student.daily.query.api.repository.PresenceStudentDailyRepository;
import com.school.presence.student.daily.query.api.response.PresenceCountedStudentDailyResponse;
import com.school.presence.student.daily.query.api.response.PresenceStudentDailyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PresenceStudentDailyQueryHandlerImpl implements PresenceStudentDailyQueryHandler {
    private final PresenceStudentDailyRepository presenceStudentDailyRepository;
    @Override
    public
    Flux<PresenceStudentDailyResponse> findPresenceStudentDailys() {
        return presenceStudentDailyRepository.findAll()
                .map(this::getPresenceStudentDaily);
    }

    @Override
    public
    Flux<PresenceStudentDailyResponse> findPresenceStudentDailyByStudent(FindByCodeQuery query) {
        return presenceStudentDailyRepository.findAllPresenceByStudent(query.code())
                .map(this::getPresenceStudentDaily);
    }
//    @Override
//    public Mono<PresenceStudentDailyResponse> findPresenceCountStudentDailyByPresenceStudentDailyCode(String code) {
//        return presenceStudentDailyRepository.findAllPresenceByStudent(code)
//                .map(this::getPresenceCountedStudentDaily); // Méthode qui transforme l'entité en List<PresenceCountedStudentDailyResponse>
//    }



    @Override
    public Mono<PresenceStudentDailyResponse> findPresenceStudentDailyByPresenceStudentDailyCode(String clothCode) {
        return presenceStudentDailyRepository.findPresenceStudentDailyByCode (clothCode)
                .map(this::getPresenceStudentDaily);
    }
   private  PresenceStudentDailyResponse getPresenceStudentDaily ( PresenceStudentDaily presenceStudentDaily ) {
        return new PresenceStudentDailyResponse(
                presenceStudentDaily.getPresenceStudentDailyId (),
                presenceStudentDaily.getCode(),
                presenceStudentDaily.getPresenceInClassCode(),
                presenceStudentDaily.getPresence(),
                presenceStudentDaily.getStudent(),
                presenceStudentDaily.getPresentStatus(),
                presenceStudentDaily.getProf(),
                presenceStudentDaily.getScheduleCode(),
                presenceStudentDaily.getAbsents(),
                presenceStudentDaily.getPresents(),
                presenceStudentDaily.getSchoolYear(),
                presenceStudentDaily.getTrimester(),
                presenceStudentDaily.getStatus(),
                presenceStudentDaily.getLogCreatedAt()

        );
    }

    private PresenceCountedStudentDailyResponse getPresenceCountedStudentDaily(PresenceStudentDaily presenceStudentDaily) {
        return new PresenceCountedStudentDailyResponse(presenceStudentDaily.getCode(), presenceStudentDaily.getAbsents(), presenceStudentDaily.getPresents(), presenceStudentDaily.getSchoolYear(), presenceStudentDaily.getTrimester()

        );
    }


//    @Override
//    public
//    Mono<DriverCreatedCommand> createDrivers ( ) throws Exception {
//        WebClient webClient = createWebClient();
//        return webClient.get()
//                .uri("http://127.0.0.1:9901/api/v1/education/driver/driver-create")
//                .retrieve()
//                .bodyToMono(DriverCreatedCommand.class);
//    }

}
