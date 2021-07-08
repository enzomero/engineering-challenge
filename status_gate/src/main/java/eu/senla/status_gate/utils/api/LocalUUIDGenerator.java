package eu.senla.status_gate.utils.api;

import java.util.UUID;

/**
 * Interface helps us to keep control in tests and generalize UUID using
 */
public interface LocalUUIDGenerator {
    UUID getUUID();

    String getUUIDasString();

    UUID formString(String uuid);
}
