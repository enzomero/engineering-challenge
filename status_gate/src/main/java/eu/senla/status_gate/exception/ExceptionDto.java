package eu.senla.status_gate.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ExceptionDto {
    private HttpStatus status;
    private String message;
}
