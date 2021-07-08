package eu.senla.status_api.dto.api;

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
