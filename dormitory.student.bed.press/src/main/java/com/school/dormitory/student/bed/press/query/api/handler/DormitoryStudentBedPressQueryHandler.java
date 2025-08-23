package com.school.dormitory.student.bed.press.query.api.handler;

import com.school.dormitory.student.bed.press.core.model.DormitoryStudentBedPress;
import com.school.dormitory.student.bed.press.query.api.response.DormitoryStudentBedPressResponse;
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
interface DormitoryStudentBedPressQueryHandler {


    Flux<DormitoryStudentBedPressResponse> findDormitoryStudentBedPresss ( );

    Mono<DormitoryStudentBedPressResponse> findDormitoryStudentBedPressById ( String clothId );

    Mono<DormitoryStudentBedPressResponse> findDormitoryStudentBedPressByDormitoryStudentBedPressCode ( String clothCode );

    DormitoryStudentBedPressResponse getDormitoryStudentBedPress( DormitoryStudentBedPress DormitoryStudentBedPress );
}