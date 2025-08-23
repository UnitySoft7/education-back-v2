//package digital.ecosystem.web.api.exception;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.net.ConnectException;
//
//@Component
//public class CustomErrorFilter extends AbstractGatewayFilterFactory<CustomErrorFilter.Config> {
//
//    public CustomErrorFilter() {
//        super(Config.class);
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return (exchange, chain) -> chain.filter(exchange)
//                .onErrorResume(ex -> {
//                    if (ex instanceof ConnectException) {
//                        try {
//                            return handleConnectionRefused(exchange);
//                        } catch (JsonProcessingException e) {
//                            throw new RuntimeException();
//                        }
//                    }
//                    return Mono.error(ex);
//                });
//    }
//
//    private Mono<Void> handleConnectionRefused(ServerWebExchange exchange) throws JsonProcessingException {
//        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND+"",
//                "Service temporairement indisponible. Veuillez réessayer plus tard.", "");
//
//        exchange.getResponse().setStatusCode(HttpStatus.SERVICE_UNAVAILABLE);
//        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
//
//        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
//                .bufferFactory().wrap(new ObjectMapper().writeValueAsBytes(errorResponse))));
//    }
//
//    public static class Config {
//    }
//}