package eu.senla.status_gate.service.api;

import eu.senla.status_gate.dto.ChangeRequestDto;
import eu.senla.status_gate.dto.PersonalInfoDto;
import eu.senla.status_gate.dto.UserDto;
import reactor.core.publisher.Mono;

public interface StateService {
    UserDto addUser(PersonalInfoDto personalInfoDto);

    Mono<UserDto> edit(final String uuid, ChangeRequestDto changeRequestDto);
}
