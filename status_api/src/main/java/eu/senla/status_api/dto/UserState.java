package eu.senla.status_api.dto;

import lombok.extern.log4j.Log4j2;

import java.util.Objects;

@Log4j2
public enum UserState {
    ADDED{
        @Override
        public UserState setState(final UserDto userDto){
            return Objects.nonNull(userDto.getExtraDataDto())
                    ? IN_CHECK.setState(userDto) : this;
        }
    },
    IN_CHECK{
        @Override
        public UserState setState(final UserDto userDto){
            return userDto.isApprover() &&
                    Objects.nonNull(userDto.getExtraDataDto())
                    ? APPROVED : this;
        }
    },
    APPROVED{
        @Override
        public UserState setState(final UserDto userDto){
            return Objects.nonNull(userDto.getContractDto()) &&
                    userDto.isApprover() &&
                    Objects.nonNull(userDto.getExtraDataDto())
                    ? ACTIVE : this;
        }
    },
    ACTIVE{
        @Override
        public UserState setState(final UserDto userDto){
            return IN_CHECK.setState(userDto);
        }
    };

    public abstract UserState setState(UserDto userDto);

}
