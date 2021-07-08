package eu.senla.status_gate.enumeration;

import eu.senla.status_gate.dto.UserDto;
import eu.senla.status_gate.dto.api.State;
import lombok.extern.log4j.Log4j2;

import java.util.Objects;

@Log4j2
public enum StateMachine implements State {
    ADDED{
        @Override
        public Value setState(final UserDto userDto){
            return Objects.nonNull(userDto.getExtraDataDto())
                    ? IN_CHECK.setState(userDto) : Value.ADDED;
        }
    },
    IN_CHECK{
        @Override
        public Value setState(final UserDto userDto){
            return Objects.nonNull(userDto.getApprover()) &&
                    Objects.nonNull(userDto.getExtraDataDto())
                    ? APPROVED.setState(userDto) : Value.IN_CHECK;
        }
    },
    APPROVED{
        @Override
        public Value setState(final UserDto userDto){
            return Objects.nonNull(userDto.getContractDto()) &&
                    Objects.nonNull(userDto.getApprover()) &&
                    Objects.nonNull(userDto.getExtraDataDto())
                    ? Value.ACTIVE : Value.APPROVED;
        }
    },
    ACTIVE{
        @Override
        public Value setState(final UserDto userDto){
            return IN_CHECK.setState(userDto);
        }
    };

    public abstract Value setState(UserDto userDto);

}
