package com.school.dormitory.daily.query.api.handler.impl;
import com.school.dormitory.daily.cmd.api.command.query.FindByCodeQuery;
import com.school.dormitory.daily.core.model.DormitoryStudentDaily;
import com.school.dormitory.daily.query.api.handler.DormitoryStudentDailyQueryHandler;
import com.school.dormitory.daily.query.api.repository.DormitoryStudentDailyRepository;
import com.school.dormitory.daily.query.api.response.DormitoryStudentDailyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@RequiredArgsConstructor
public class DormitoryStudentDailyQueryHandlerImpl implements DormitoryStudentDailyQueryHandler {
    private final DormitoryStudentDailyRepository dormitoryStudentDailyRepository;
    @Override
    public
    Flux<DormitoryStudentDailyResponse> findDormitoryStudentDailys ( ) {
        return dormitoryStudentDailyRepository.findAll()
                .map(this::getDormitoryStudentDaily);
    }

    @Override
    public
    Flux<DormitoryStudentDailyResponse> findDormitoryStudentDailyByStudent(FindByCodeQuery query) {
        return dormitoryStudentDailyRepository.findAllDormitoryByStudent(query.code())
                .map(this::getDormitoryStudentDaily);
    }
    @Override
    public
    Mono<DormitoryStudentDailyResponse> findDormitoryStudentDailyById ( String clothId ) {
        return dormitoryStudentDailyRepository.findById(clothId)
                .map(this::getDormitoryStudentDaily);
    }
    @Override
    public
    Mono<DormitoryStudentDailyResponse> findDormitoryStudentDailyByDormitoryStudentDailyCode ( String clothCode ) {
        return dormitoryStudentDailyRepository.findDormitoryStudentDailyByCode (clothCode)
                .map(this::getDormitoryStudentDaily);
    }
    @Override
    public
    DormitoryStudentDailyResponse getDormitoryStudentDaily ( DormitoryStudentDaily dormitoryStudentDaily ) {
        return new DormitoryStudentDailyResponse(
                dormitoryStudentDaily.getDormitoryStudentDailyId (),
                dormitoryStudentDaily.getCode(),
                dormitoryStudentDaily.getDormitoryInClassCode(),
                dormitoryStudentDaily.getDormitory(),
                dormitoryStudentDaily.getStudent(),
                dormitoryStudentDaily.getPresentStatus(),
                dormitoryStudentDaily.getProf(),
                dormitoryStudentDaily.getScheduleCode(),
                dormitoryStudentDaily.getAbsents(),
                dormitoryStudentDaily.getPresents(),
                dormitoryStudentDaily.getSchoolYear(),
                dormitoryStudentDaily.getTrimester(),
                dormitoryStudentDaily.getStatus(),
                dormitoryStudentDaily.getLogCreatedAt()

        );
    }




//    @Override
//    public
//    Mono<AllLookupDriverResponse> getDrivers ( ) ,
//    throws Exception {
//        WebClient webClient = createWebClient();
//        return webClient.get()
//                .uri("http://127.0.0.1:9901/api/v1/education/driver/driver-lookup/get-drivers")
//                .retrieve()
//                .bodyToMono(AllLookupDriverResponse.class);
//    }


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
