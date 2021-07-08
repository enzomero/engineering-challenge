package eu.senla.status_gate.exception.handler;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import eu.senla.status_gate.exception.ExceptionDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@ControllerAdvice
public class GateExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ InvalidDefinitionException.class })
    public ResponseEntity<ExceptionDto> objectMapperExceptions(Exception ex) {
        log.error(ex.getLocalizedMessage(), ex);
        ExceptionDto exceptionDto = ExceptionDto.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getLocalizedMessage())
                .build();
        return new ResponseEntity<>(exceptionDto, new HttpHeaders(), exceptionDto.getStatus());
    }

    @ExceptionHandler({ SecurityException.class })
    public ResponseEntity<ExceptionDto> kafkaSendExceptions(Exception ex) {
        log.error(ex.getLocalizedMessage(), ex);
        ExceptionDto exceptionDto = ExceptionDto.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(ex.getLocalizedMessage())
                .build();
        return new ResponseEntity<>(exceptionDto, new HttpHeaders(), exceptionDto.getStatus());
    }
}
