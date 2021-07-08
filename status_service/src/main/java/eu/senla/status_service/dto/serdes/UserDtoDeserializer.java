package eu.senla.status_service.dto.serdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.status_service.dto.UserDto;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

@Log4j2
@NoArgsConstructor
public class UserDtoDeserializer implements Deserializer<UserDto> {
    private final static ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void configure(final Map map, final boolean b) {

    }

    @Override
    public UserDto deserialize(final String s, final byte[] bytes) {
        try {
            return objectMapper.readValue(bytes, UserDto.class);
        } catch (Exception e) {
            log.error(String.format("Fail to deserialize: [%s]%n%s", new String(bytes), e.getMessage()));
            return null;
        }
    }

    @Override
    public void close() {

    }
}
