package com.school.dormitory.daily.query.api.handler;

import com.school.dormitory.daily.cmd.api.command.query.FindByCodeQuery;
import com.school.dormitory.daily.core.model.DormitoryStudentDaily;
import com.school.dormitory.daily.query.api.response.DormitoryStudentDailyResponse;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.InputStream;
import java.security.KeyStore;

public
interface DormitoryStudentDailyQueryHandler {

    Flux<DormitoryStudentDailyResponse> findDormitoryStudentDailys ( );

    Flux<DormitoryStudentDailyResponse> findDormitoryStudentDailyByStudent(FindByCodeQuery query);

    Mono<DormitoryStudentDailyResponse> findDormitoryStudentDailyById (String clothId );

    Mono<DormitoryStudentDailyResponse> findDormitoryStudentDailyByDormitoryStudentDailyCode ( String clothCode );

    DormitoryStudentDailyResponse getDormitoryStudentDaily( DormitoryStudentDaily DormitoryStudentDaily );
}