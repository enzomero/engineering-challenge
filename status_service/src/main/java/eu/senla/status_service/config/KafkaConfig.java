package eu.senla.status_service.config;

import eu.senla.status_service.dto.UserDto;
import eu.senla.status_service.dto.serdes.UserDtoDeserializer;
import eu.senla.status_service.dto.serdes.UserDtoSerializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;
import java.util.Properties;

@Log4j2
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {
    public static final String T_INPUT = "state_machine";
    public static final String T_OUTPUT = "save";
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public Properties properties() {
        Properties streamProperties = new Properties();
        streamProperties.put(StreamsConfig.APPLICATION_ID_CONFIG, "test");
        streamProperties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return streamProperties;
    }

    @Bean
    public void topology() {
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, UserDto> stream = streamsBuilder
                .stream(T_INPUT, Consumed.with(Serdes.String(),
                        Serdes.serdeFrom(new UserDtoSerializer(), new UserDtoDeserializer())
                ));

        KStream<String, UserDto> peek = stream
                .mapValues(value -> value)
                .filter((key, value) -> Objects.nonNull(value))//ignore fails
                .peek((key, value) -> log.info(String.format("STREAM[K:%s|V:%S]", key, value)));

        peek.to(T_OUTPUT, Produced.with(Serdes.String(),
                Serdes.serdeFrom(new UserDtoSerializer(), new UserDtoDeserializer())
        ));
        new KafkaStreams(streamsBuilder.build(), properties()).start();
    }

    @Bean
    public StreamsBuilder streamsBuilder() {
        return new StreamsBuilder();
    }
}
