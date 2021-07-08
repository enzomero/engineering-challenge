package eu.senla.status_service.dto.serdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.status_service.dto.UserDto;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * This custom specific serializer for Kafka streams
 */
@Log4j2
@NoArgsConstructor
public class UserDtoSerializer implements Serializer<UserDto> {

    private final static ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void configure(final Map map, final boolean b) {

    }

    @Override
    public byte[] serialize(final String s, final UserDto singleNotificationDto) {
        try {
            return objectMapper.writeValueAsString(singleNotificationDto).getBytes();
        } catch (Exception e) {
            log.error(String.format("Fail to serialize: [%s]%n%s", singleNotificationDto.toString(), e.getMessage()));
            return null;
        }
    }

    @Override
    public void close() {

    }
}
