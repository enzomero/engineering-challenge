package eu.senla.status_gate.service;

import eu.senla.status_gate.dto.*;
import eu.senla.status_gate.exception.SequenceException;
import eu.senla.status_gate.service.api.StateApiAdaptor;
import eu.senla.status_gate.utils.api.LocalUUIDGenerator;
import eu.senla.status_gate.service.api.StateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Log4j2
@Service
@RequiredArgsConstructor
public class StateServiceImpl implements StateService {

    @Value("${spring.kafka.topic-create}")
    private String topicCreate;

    private final LocalUUIDGenerator generator;
    private final KafkaTemplate<String, UserDto> kafkaTemplate;
    private final StateApiAdaptor stateApiAdaptor;

    @Override
    public UserDto addUser(final String personalInfoDto) {
        final UserDto userDto = UserDto.builder()
                .uuid(generator.getUUID())
                .user(personalInfoDto)
                .build();
        return tryToSend(topicCreate, userDto);
    }

    @Override
    public Mono<UserDto> edit(final ChangeRequestDto changeRequestDto) {
        return stateApiAdaptor.findUser(changeRequestDto.getUuid().toString())
                .map(userDto -> userDto.setExtraDataDto(changeRequestDto.getExtraDataDto()))
                .map(userDto -> userDto.setContractDto(changeRequestDto.getContractDto()))
                .checkpoint()
                .map(userDto -> tryToSend(topicCreate, userDto));
    }

    private UserDto tryToSend(final String topicCreate, final UserDto userDto) {
        try {
            kafkaTemplate.send(topicCreate, userDto);
            log.debug(String.format("The user [%s] sent to the que", userDto.getUuid()));
            return userDto;
        } catch (Exception e){
            throw new SequenceException("The user doesnt fit to que", userDto);
        }
    }
}
