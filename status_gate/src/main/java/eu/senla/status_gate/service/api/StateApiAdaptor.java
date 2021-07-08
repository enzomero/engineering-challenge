package eu.senla.status_gate.service.api;

import eu.senla.status_gate.dto.UserDto;
import reactor.core.publisher.Mono;

public interface StateApiAdaptor {
    Mono<UserDto> findUser(String uuid);
}
