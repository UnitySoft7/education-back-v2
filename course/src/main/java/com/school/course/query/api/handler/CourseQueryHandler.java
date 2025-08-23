package com.school.course.query.api.handler;

import com.school.course.core.model.Course;
import com.school.course.query.api.response.CourseResponse;
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
interface CourseQueryHandler {
    static
    WebClient createWebClient ( ) throws Exception {
    ClassPathResource resource         = new ClassPathResource("keystore.p12");
    String            keystorePassword = "123456";

    KeyStore keyStore = KeyStore.getInstance("PKCS12");
    try (InputStream keyStoreStream = resource.getInputStream()) {
        keyStore.load(keyStoreStream, keystorePassword.toCharArray());
    }
    KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
    keyManagerFactory.init(keyStore, keystorePassword.toCharArray());

    TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
    trustManagerFactory.init(keyStore);

    SslContext sslContext = SslContextBuilder.forClient()
            .keyManager(keyManagerFactory)
            .trustManager(trustManagerFactory)
            .build();

    HttpClient httpClient = HttpClient.create()
            .secure(sslSpec -> sslSpec.sslContext(sslContext));

    return WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector (httpClient))
            .build();
}

    Flux<CourseResponse> findCourses ( );

    Mono<CourseResponse> findCourseById ( String courseId );

    Mono<CourseResponse> findCourseByCourseCode ( String courseCode );

    Flux<CourseResponse> findCourseByEstablishment(String establishmentCode);

    CourseResponse getCourse(Course Course );
}