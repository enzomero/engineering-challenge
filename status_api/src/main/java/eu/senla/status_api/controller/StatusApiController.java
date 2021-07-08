package eu.senla.status_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.status_api.dto.UserDto;
import eu.senla.status_api.service.api.StatusApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StatusApiController {

    private final StatusApiService statusApiService;
    private final ObjectMapper objectMapper;

    @GetMapping("/user/{uuid}")
    public UserDto findUser(final @PathVariable("uuid") String uuid){
        return objectMapper.convertValue(statusApiService.getProfile(uuid), UserDto.class);
    }
}
