package digital.ecosystem.web.api.exception;

import java.io.Serializable;

public record ErrorResponse(String errorCode, String message,
                            String details) implements Serializable {}
