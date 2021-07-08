package eu.senla.status_gate.dto;

import lombok.*;

import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class UserDto {
    private UUID uuid;
    private String user;
    private UserState userState = UserState.ADDED;
    private String contractDto;
    private String extraDataDto;
    private boolean approver;

    @Builder
    private UserDto(final UUID uuid, final String user) {
        this.uuid = uuid;
        this.user = user;
    }

    public UserDto setContractDto(final String contractDto) {
        this.contractDto = contractDto;
        this.userState = userState.setState(this);
        return this;
    }

    public UserDto setExtraDataDto(final String extraDataDto) {
        this.extraDataDto = extraDataDto;
        this.userState = userState.setState(this);
        return this;
    }

    public UserDto setApprover(final boolean approver) {
        this.approver = approver;
        this.userState = userState.setState(this);
        return this;
    }

}
