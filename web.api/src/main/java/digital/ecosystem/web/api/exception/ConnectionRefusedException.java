package digital.ecosystem.web.api.exception;

public class ConnectionRefusedException extends RuntimeException {
    public ConnectionRefusedException(String message) {
        super(message);
    }
}
