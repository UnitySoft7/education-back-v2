package digital.ecosystem.web.api.test;

public class AuthorizationException extends RuntimeException {
    public AuthorizationException() {
        super("Authorization information is missing.");
    }
}