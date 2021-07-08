package eu.senla.status_gate.utils;

import eu.senla.status_gate.utils.api.LocalUUIDGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LocalUUIDGeneratorImpl implements LocalUUIDGenerator {
    @Override
    public UUID getUUID(){
        return UUID.randomUUID();
    }

    @Override
    public String getUUIDasString(){
        return UUID.randomUUID().toString();
    }

    @Override
    public UUID formString(String uuid){
        return UUID.fromString(uuid);
    }
}
