package eu.senla.status_gate.service;

import eu.senla.status_gate.dto.UserDto;
import eu.senla.status_gate.exception.SequenceException;
import eu.senla.status_gate.service.api.StateApiAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class StateApiAdaptorImpl implements StateApiAdaptor {

    private final WebClient webClient;
    @Value(value = "${spring.api.url}")
    private String targetUrl;

    @Override
    public Mono<UserDto> findUser(final String uuid) {
        final URI uri = URI.create(String.format("%s/%s", targetUrl, uuid));
        return webClient.get()
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, ClientResponse::createException)
                .bodyToMono(UserDto.class)
//                .metrics() add metrics
                .onErrorResume(WebClientResponseException.class, e -> Mono.error(new SequenceException("Not found", uuid)))
                .onErrorResume(Exception.class, throwable -> Mono.error(new SequenceException("The resource serve unreachable", uuid)));
    }
}
