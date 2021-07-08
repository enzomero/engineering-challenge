package eu.senla.status_gate.controller;

import eu.senla.status_gate.dto.ChangeRequestDto;
import eu.senla.status_gate.dto.PersonalInfoDto;
import eu.senla.status_gate.dto.UserDto;
import eu.senla.status_gate.service.api.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
public class GateController {

    private final StateService stateService;

    @PostMapping("/user")
    public UserDto addUser(final @RequestBody PersonalInfoDto personalInfoDto) {
        return stateService.addUser(personalInfoDto);
    }

    @PutMapping("/user/{uuid}")
    public Mono<UserDto> editUser(final @PathVariable("uuid") String uuid, final @RequestBody ChangeRequestDto changeRequestDto) {
        return stateService.edit(uuid, changeRequestDto);
    }
}
