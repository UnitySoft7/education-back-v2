package com.school.schedule.query.api.handler.impl;

import com.school.schedule.cmd.api.query.FindByCodeQuery;
import com.school.schedule.core.model.Schedule;
import com.school.schedule.query.api.handler.ScheduleQueryHandler;
import com.school.schedule.query.api.repository.ScheduleRepository;
import com.school.schedule.query.api.response.ScheduleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ScheduleQueryHandlerImpl implements ScheduleQueryHandler {
    private final ScheduleRepository scheduleRepository;

    @Override
    public Flux<ScheduleResponse> findSchedules() {
        return scheduleRepository.findAll().map(this::getSchedule);
    }

    @Override
    public Mono<ScheduleResponse> findScheduleByScheduleCode(String code) {
        return scheduleRepository.findScheduleByCode(code).map(this::getSchedule);
    }

    @Override
    public Flux<ScheduleResponse> findAllScheduleByClassCode(FindByCodeQuery query) {
        return scheduleRepository.findAllScheduleByClassroomCode(query.code()).map(this::getSchedule);
    }

    private ScheduleResponse getSchedule(Schedule schedule) {
        return new ScheduleResponse(
                schedule.getScheduleId(), schedule.getCode(),

                schedule.getEstablishmentName(),
                schedule.getEstablishmentCode(),
                schedule.getSectionName(),
                schedule.getSectionCode(),
                schedule.getClassroomName(),
                schedule.getClassroomCode(), schedule.getMonday(), schedule.getTuesday(), schedule.getWednesday(), schedule.getThusday(),
                schedule.getFriday(), schedule.getSaturday(), schedule.getSunday()

        );
    }
}
//    @Override
//    public Mono<List<ScheduleClassResponse>> getScheduleTest(String code) {
//        return scheduleRepository.findAllScheduleByESC(code)
//                .collectList()
//                .flatMap(schedules -> {
//                    List<ScheduleClassResponse> scheduleClassResponses = new ArrayList<>();
//                    int i = 0;
//                    ScheduleClassResponse s1 = new ScheduleClassResponse();
//                    for (Schedule schedule : schedules) {
//                        if (i == 0) {
//                            s1.setLundi(schedule.getDayIndex1Course());
//                        }
//                        if (i == 1) {
//                            s1.setMardi(schedule.getDayIndex1Course());
//                        }
//                        if (i == 2) {
//                            s1.setMercredi(schedule.getDayIndex1Course());
//                        }
//                        if (i == 3) {
//                            s1.setJeudi(schedule.getDayIndex1Course());
//                        }
//                        if (i == 4) {
//                            s1.setVendredi(schedule.getDayIndex1Course());
//                        }
//                        if (i == 5) {
//                            s1.setSamedi(schedule.getDayIndex1Course());
//                        }
//                        if (i == 6) {
//                            s1.setDimanche(schedule.getDayIndex1Course());
//                        }
//                        i += 1;
//                    }
//                    i = 0;
//                    ScheduleClassResponse s2 = new ScheduleClassResponse();
//                    for (Schedule schedule : schedules) {
//                        if (i == 0) {
//                            s2.setLundi(schedule.getDayIndex2Course());
//                        }
//                        if (i == 1) {
//                            s2.setMardi(schedule.getDayIndex2Course());
//                        }
//                        if (i == 2) {
//                            s2.setMercredi(schedule.getDayIndex2Course());
//                        }
//                        if (i == 3) {
//                            s2.setJeudi(schedule.getDayIndex2Course());
//                        }
//                        if (i == 4) {
//                            s2.setVendredi(schedule.getDayIndex2Course());
//                        }
//                        if (i == 5) {
//                            s2.setSamedi(schedule.getDayIndex2Course());
//                        }
//                        if (i == 6) {
//                            s2.setDimanche(schedule.getDayIndex2Course());
//                        }
//                        i += 1;
//                    }
//                    i = 0;
//                    ScheduleClassResponse s3 = new ScheduleClassResponse();
//                    for (Schedule schedule : schedules) {
//                        if (i == 0) {
//                            s3.setLundi(schedule.getDayIndex3Course());
//                        }
//                        if (i == 1) {
//                            s3.setMardi(schedule.getDayIndex3Course());
//                        }
//                        if (i == 2) {
//                            s3.setMercredi(schedule.getDayIndex3Course());
//                        }
//                        if (i == 3) {
//                            s3.setJeudi(schedule.getDayIndex3Course());
//                        }
//                        if (i == 4) {
//                            s3.setVendredi(schedule.getDayIndex3Course());
//                        }
//                        if (i == 5) {
//                            s3.setSamedi(schedule.getDayIndex3Course());
//                        }
//                        if (i == 6) {
//                            s3.setDimanche(schedule.getDayIndex3Course());
//                        }
//                        i += 1;
//                    }
//                    i = 0;
//                    ScheduleClassResponse s4 = new ScheduleClassResponse();
//                    for (Schedule schedule : schedules) {
//                        if (i == 0) {
//                            s4.setLundi(schedule.getDayIndex4Course());
//                        }
//                        if (i == 1) {
//                            s4.setMardi(schedule.getDayIndex4Course());
//                        }
//                        if (i == 2) {
//                            s4.setMercredi(schedule.getDayIndex4Course());
//                        }
//                        if (i == 3) {
//                            s4.setJeudi(schedule.getDayIndex4Course());
//                        }
//                        if (i == 4) {
//                            s4.setVendredi(schedule.getDayIndex4Course());
//                        }
//                        if (i == 5) {
//                            s4.setSamedi(schedule.getDayIndex4Course());
//                        }
//                        if (i == 6) {
//                            s4.setDimanche(schedule.getDayIndex4Course());
//                        }
//                        i += 1;
//                    }
//                    i = 0;
//                    ScheduleClassResponse s5 = new ScheduleClassResponse();
//                    for (Schedule schedule : schedules) {
//                        if (i == 0) {
//                            s5.setLundi(schedule.getDayIndex5Course());
//                        }
//                        if (i == 1) {
//                            s5.setMardi(schedule.getDayIndex5Course());
//                        }
//                        if (i == 2) {
//                            s5.setMercredi(schedule.getDayIndex5Course());
//                        }
//                        if (i == 3) {
//                            s5.setJeudi(schedule.getDayIndex5Course());
//                        }
//                        if (i == 4) {
//                            s5.setVendredi(schedule.getDayIndex5Course());
//                        }
//                        if (i == 5) {
//                            s5.setSamedi(schedule.getDayIndex5Course());
//                        }
//                        if (i == 6) {
//                            s5.setDimanche(schedule.getDayIndex5Course());
//                        }
//                        i += 1;
//                    }
//                    i = 0;
//                    ScheduleClassResponse s6 = new ScheduleClassResponse();
//                    for (Schedule schedule : schedules) {
//                        if (i == 0) {
//                            s6.setLundi(schedule.getDayIndex6Course());
//                        }
//                        if (i == 1) {
//                            s6.setMardi(schedule.getDayIndex6Course());
//                        }
//                        if (i == 2) {
//                            s6.setMercredi(schedule.getDayIndex6Course());
//                        }
//                        if (i == 3) {
//                            s6.setJeudi(schedule.getDayIndex6Course());
//                        }
//                        if (i == 4) {
//                            s6.setVendredi(schedule.getDayIndex6Course());
//                        }
//                        if (i == 5) {
//                            s6.setSamedi(schedule.getDayIndex6Course());
//                        }
//                        if (i == 6) {
//                            s6.setDimanche(schedule.getDayIndex6Course());
//                        }
//                        i += 1;
//                    }
//                    i = 0;
//                    ScheduleClassResponse s7 = new ScheduleClassResponse();
//                    for (Schedule schedule : schedules) {
//                        if (i == 0) {
//                            s7.setLundi(schedule.getDayIndex7Course());
//                        }
//                        if (i == 1) {
//                            s7.setMardi(schedule.getDayIndex7Course());
//                        }
//                        if (i == 2) {
//                            s7.setMercredi(schedule.getDayIndex7Course());
//                        }
//                        if (i == 3) {
//                            s7.setJeudi(schedule.getDayIndex7Course());
//                        }
//                        if (i == 4) {
//                            s7.setVendredi(schedule.getDayIndex7Course());
//                        }
//                        if (i == 5) {
//                            s7.setSamedi(schedule.getDayIndex7Course());
//                        }
//                        if (i == 6) {
//                            s7.setDimanche(schedule.getDayIndex7Course());
//                        }
//                        i += 1;
//                    }
//                    i = 0;
//                    ScheduleClassResponse s8 = new ScheduleClassResponse();
//                    for (Schedule schedule : schedules) {
//                        if (i == 0) {
//                            s8.setLundi(schedule.getDayIndex8Course());
//                        }
//                        if (i == 1) {
//                            s8.setMardi(schedule.getDayIndex8Course());
//                        }
//                        if (i == 2) {
//                            s8.setMercredi(schedule.getDayIndex8Course());
//                        }
//                        if (i == 3) {
//                            s8.setJeudi(schedule.getDayIndex8Course());
//                        }
//                        if (i == 4) {
//                            s8.setVendredi(schedule.getDayIndex8Course());
//                        }
//                        if (i == 5) {
//                            s8.setSamedi(schedule.getDayIndex8Course());
//                        }
//                        if (i == 6) {
//                            s8.setDimanche(schedule.getDayIndex8Course());
//                        }
//                        i += 1;
//                    }
//                    scheduleClassResponses.add(s1);
//                    scheduleClassResponses.add(s2);
//                    scheduleClassResponses.add(s3);
//                    scheduleClassResponses.add(s4);
//                    scheduleClassResponses.add(s5);
//                    scheduleClassResponses.add(s6);
//                    scheduleClassResponses.add(s7);
//                    scheduleClassResponses.add(s8);
//
//                    return Mono.just(scheduleClassResponses);
//                });
//
//    }
//
//
//
//    @Override
//    public Mono<AllLookupSectionResponse> getSections() throws Exception {
//        WebClient webClient = WebClient.create();
//        return webClient.get().uri("http://127.0.0.1:9825/api/v1/education/section/lookup-section/get-sections").retrieve().bodyToMono(AllLookupSectionResponse.class);
//    }
//}

//

//
//private ScheduleIndexResponse getIndex1Schedule(Schedule schedule) {
//    return new ScheduleIndexResponse(schedule.getScheduleName(), schedule.getCode(),
//            schedule.getStartOn(), schedule.getEndOn(), schedule.getESC(),
//            schedule.getESCC(), schedule.getEstablishmentName(),
//            schedule.getEstablishmentCode(), schedule.getSectionName(),
//            schedule.getSectionCode(), schedule.getCourseCode(),
//            schedule.getClassroomName(), schedule.getClassroomCode(),
//            schedule.getDay(), schedule.getIndex1(), schedule.getDayIndex1Course());
//
//}
//
//private ScheduleIndexResponse getIndex2Schedule(Schedule schedule) {
//    return new ScheduleIndexResponse(schedule.getScheduleName(), schedule.getCode(),
//            schedule.getStartOn(), schedule.getEndOn(), schedule.getESC(),
//            schedule.getESCC(), schedule.getEstablishmentName(),
//            schedule.getEstablishmentCode(), schedule.getSectionName(),
//            schedule.getSectionCode(), schedule.getCourseCode(),
//            schedule.getClassroomName(), schedule.getClassroomCode(),
//            schedule.getDay(), schedule.getIndex2(), schedule.getDayIndex2Course());
//
//}
//
//private ScheduleIndexResponse getIndex3Schedule(Schedule schedule) {
//    return new ScheduleIndexResponse(schedule.getScheduleName(), schedule.getCode(),
//            schedule.getStartOn(), schedule.getEndOn(), schedule.getESC(),
//            schedule.getESCC(), schedule.getEstablishmentName(),
//            schedule.getEstablishmentCode(), schedule.getSectionName(),
//            schedule.getSectionCode(), schedule.getCourseCode(),
//            schedule.getClassroomName(), schedule.getClassroomCode(),
//            schedule.getDay(), schedule.getIndex3(), schedule.getDayIndex3Course());
//
//}
//
//private ScheduleIndexResponse getIndex4Schedule(Schedule schedule) {
//    return new ScheduleIndexResponse(schedule.getScheduleName(), schedule.getCode(),
//            schedule.getStartOn(), schedule.getEndOn(), schedule.getESC(),
//            schedule.getESCC(), schedule.getEstablishmentName(),
//            schedule.getEstablishmentCode(), schedule.getSectionName(),
//            schedule.getSectionCode(), schedule.getCourseCode(),
//            schedule.getClassroomName(), schedule.getClassroomCode(),
//            schedule.getDay(), schedule.getIndex4(), schedule.getDayIndex4Course());
//
//}
//private ScheduleIndexResponse getIndex5Schedule(Schedule schedule) {
//    return new ScheduleIndexResponse(schedule.getScheduleName(), schedule.getCode(),
//            schedule.getStartOn(), schedule.getEndOn(), schedule.getESC(),
//            schedule.getESCC(), schedule.getEstablishmentName(),
//            schedule.getEstablishmentCode(), schedule.getSectionName(),
//            schedule.getSectionCode(), schedule.getCourseCode(),
//            schedule.getClassroomName(), schedule.getClassroomCode(),
//            schedule.getDay(), schedule.getIndex5(), schedule.getDayIndex5Course());
//
//}
//private ScheduleIndexResponse getIndex6Schedule(Schedule schedule) {
//    return new ScheduleIndexResponse(schedule.getScheduleName(), schedule.getCode(),
//            schedule.getStartOn(), schedule.getEndOn(), schedule.getESC(),
//            schedule.getESCC(), schedule.getEstablishmentName(),
//            schedule.getEstablishmentCode(), schedule.getSectionName(),
//            schedule.getSectionCode(), schedule.getCourseCode(),
//            schedule.getClassroomName(), schedule.getClassroomCode(),
//            schedule.getDay(), schedule.getIndex6(), schedule.getDayIndex6Course());
//
//}
//private ScheduleIndexResponse getIndex7Schedule(Schedule schedule) {
//    return new ScheduleIndexResponse(schedule.getScheduleName(), schedule.getCode(),
//            schedule.getStartOn(), schedule.getEndOn(), schedule.getESC(),
//            schedule.getESCC(), schedule.getEstablishmentName(),
//            schedule.getEstablishmentCode(), schedule.getSectionName(),
//            schedule.getSectionCode(), schedule.getCourseCode(),
//            schedule.getClassroomName(), schedule.getClassroomCode(),
//            schedule.getDay(), schedule.getIndex7(), schedule.getDayIndex7Course());
//
//}
//private ScheduleIndexResponse getIndex8Schedule(Schedule schedule) {
//    return new ScheduleIndexResponse(schedule.getScheduleName(), schedule.getCode(),
//            schedule.getStartOn(), schedule.getEndOn(), schedule.getESC(),
//            schedule.getESCC(), schedule.getEstablishmentName(),
//            schedule.getEstablishmentCode(), schedule.getSectionName(),
//            schedule.getSectionCode(), schedule.getCourseCode(),
//            schedule.getClassroomName(), schedule.getClassroomCode(),
//            schedule.getDay(), schedule.getIndex8(), schedule.getDayIndex8Course());



//
//public Mono<ScheduleIndexResponse> findScheduleByScheduleCodeAndIndex(FindByCodeAndIndexQuery query) {
//    return scheduleRepository.findScheduleByCodeAndIndex1(query.code(), query.index()).map(this::getIndex1Schedule);
//}
//
//@Override
//public Mono<ScheduleIndexResponse> findScheduleByScheduleCodeAndIndex2(FindByCodeAndIndexQuery query) {
//    return scheduleRepository.findScheduleByCodeAndIndex2(query.code(), query.index()).map(this::getIndex2Schedule);
//}
//
//@Override
//public Mono<ScheduleIndexResponse> findScheduleByScheduleCodeAndIndex3(FindByCodeAndIndexQuery query) {
//    return scheduleRepository.findScheduleByCodeAndIndex3(query.code(), query.index()).map(this::getIndex3Schedule);
//}
//
//@Override
//public Mono<ScheduleIndexResponse> findScheduleByScheduleCodeAndIndex4(FindByCodeAndIndexQuery query) {
//    return scheduleRepository.findScheduleByCodeAndIndex4(query.code(), query.index()).map(this::getIndex4Schedule);
//}
//
//@Override
//public Mono<ScheduleIndexResponse> findScheduleByScheduleCodeAndIndex5(FindByCodeAndIndexQuery query) {
//    return scheduleRepository.findScheduleByCodeAndIndex5(query.code(), query.index()).map(this::getIndex5Schedule);
//}
//
//@Override
//public Mono<ScheduleIndexResponse> findScheduleByScheduleCodeAndIndex6(FindByCodeAndIndexQuery query) {
//    return scheduleRepository.findScheduleByCodeAndIndex6(query.code(), query.index()).map(this::getIndex6Schedule);
//}
//
//@Override
//public Mono<ScheduleIndexResponse> findScheduleByScheduleCodeAndIndex7(FindByCodeAndIndexQuery query) {
//    return scheduleRepository.findScheduleByCodeAndIndex7(query.code(), query.index()).map(this::getIndex7Schedule);
//}
//
//@Override
//public Mono<ScheduleIndexResponse> findScheduleByScheduleCodeAndIndex8(FindByCodeAndIndexQuery query) {
//    return scheduleRepository.findScheduleByCodeAndIndex8(query.code(), query.index()).map(this::getIndex8Schedule);
//}
//
////
////}
