//package digital.ecosystem.web.api.test;
//
//import io.netty.handler.ssl.SslContext;
//import io.netty.handler.ssl.SslContextBuilder;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.client.reactive.ReactorClientHttpConnector;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.server.ResponseStatusException;
//import reactor.core.publisher.Mono;
//import reactor.netty.http.client.HttpClient;
//
//import javax.net.ssl.KeyManagerFactory;
//import javax.net.ssl.TrustManagerFactory;
//import java.io.InputStream;
//import java.nio.charset.StandardCharsets;
//import java.security.KeyStore;
//import java.util.Map;
//
//@Component
//public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
//
////    private final WebClient.Builder webClientBuilder;
//
//    public AuthFilter(WebClient.Builder webClientBuilder) {
//        super(Config.class);
////        this.webClientBuilder = webClientBuilder;
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return (exchange, chain) -> {
//            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
//                throw new AuthorizationException();
//            }
//            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
//            String[] parts = authHeader.split(" ");
//
//            if (parts.length != 2 || !"Bearer".equals(parts[0])) {
//                throw new RuntimeException("Incorrect authorization structure");
//            }
//            try {
//                WebClient webClient = createWebClient();
//                return webClient.get()
//                        .uri("https://127.0.0.1:9523/api/v1/payment/user/auth/verify/token/verify/" + parts[1])
//                        .retrieve()
//                        .bodyToMono(UserDto.class)
//                        .map(userDto -> {
//                            exchange.getRequest()
//                                    .mutate()
//                                    .header("X-auth-user-id", String.valueOf(userDto.getId()));
//                            return exchange;
//                        })
//                        .flatMap(chain::filter)
//                        .onErrorResume(e -> Mono.error(new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Service d'authentification indisponible", e)));
//            } catch (Exception e) {
//                throw new RuntimeException("message");
//            }
//        };
//    }
//
//    public static class Config {
//    }
//
//    public static WebClient createWebClient() throws Exception {
//        ClassPathResource resource = new ClassPathResource("keystore.p12");
//        String keystorePassword = "123456";
//
//        KeyStore keyStore = KeyStore.getInstance("PKCS12");
//        try (InputStream keyStoreStream = resource.getInputStream()) {
//            keyStore.load(keyStoreStream, keystorePassword.toCharArray());
//        }
//        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
//        keyManagerFactory.init(keyStore, keystorePassword.toCharArray());
//
//        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//        trustManagerFactory.init(keyStore);
//
//        SslContext sslContext = SslContextBuilder.forClient()
//                .keyManager(keyManagerFactory)
//                .trustManager(trustManagerFactory)
//                .build();
//
//        HttpClient httpClient = HttpClient.create()
//                .secure(sslSpec -> sslSpec.sslContext(sslContext));
//
//        return WebClient.builder()
//                .clientConnector(new ReactorClientHttpConnector(httpClient))
//                .build();
//    }
//}
//
