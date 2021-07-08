package eu.senla.status_gate.exception;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SequenceException extends RuntimeException {
    public SequenceException(final String s, final Object message) {
        super(s);
        log.error(String.format("The data was lost: [%s]", message.toString()));
    }
}
