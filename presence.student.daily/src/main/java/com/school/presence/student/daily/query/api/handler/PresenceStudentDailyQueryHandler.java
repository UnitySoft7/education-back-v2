package com.school.presence.student.daily.query.api.handler;

import com.school.presence.student.daily.cmd.api.command.query.FindByCodeQuery;
import com.school.presence.student.daily.core.model.PresenceStudentDaily;
import com.school.presence.student.daily.query.api.response.PresenceCountedStudentDailyResponse;
import com.school.presence.student.daily.query.api.response.PresenceStudentDailyResponse;
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
interface PresenceStudentDailyQueryHandler {
    Flux<PresenceStudentDailyResponse> findPresenceStudentDailys();

    Flux<PresenceStudentDailyResponse> findPresenceStudentDailyByStudent(FindByCodeQuery query);

    Mono<PresenceStudentDailyResponse> findPresenceStudentDailyByPresenceStudentDailyCode(String clothCode);
}