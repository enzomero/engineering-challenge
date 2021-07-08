package eu.senla.status_service.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
public class UserDto {
    private UUID uuid;
    private PersonalInfoDto user;
    private UserState userState = UserState.ADDED;
    private ContractDto contractDto;
    private ExtraDataDto extraDataDto;
    private PersonalInfoDto approver;

    @Builder
    private UserDto(final UUID uuid, final PersonalInfoDto user) {
        this.uuid = uuid;
        this.user = user;
    }

    public UserDto setContractDto(final ContractDto contractDto) {
        this.contractDto = contractDto;
        this.userState = userState.setState(this);
        return this;
    }

    public UserDto setExtraDataDto(final ExtraDataDto extraDataDto) {
        this.extraDataDto = extraDataDto;
        this.userState = userState.setState(this);
        return this;
    }

    public UserDto setApprover(final PersonalInfoDto approver) {
        this.approver = approver;
        this.userState = userState.setState(this);
        return this;
    }

}
