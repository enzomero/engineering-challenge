package eu.senla.status_gate.dto.api;

import eu.senla.status_gate.enumeration.StateMachine;

/**
 * This interface allows us share states for dtos and entities and encapsulate @See({@link StateMachine})
 */
public interface State {
    enum Value {
        ADDED,
        IN_CHECK,
        APPROVED,
        ACTIVE;
    }
}
