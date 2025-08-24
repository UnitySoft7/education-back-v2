package com.school.presence.teacher.query.api.handler;

import com.school.presence.teacher.core.model.PresenceTeacher;
import com.school.presence.teacher.query.api.response.PresenceTeacherResponse;
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
interface PresenceTeacherQueryHandler {
    Flux<PresenceTeacherResponse> findPresenceTeachers ( );

    Mono<PresenceTeacherResponse> findPresenceTeacherById ( String clothId );

    Mono<PresenceTeacherResponse> findPresenceTeacherByPresenceTeacherCode ( String clothCode );

}