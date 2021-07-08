package eu.senla.status_gate.dto;

import eu.senla.status_gate.dto.api.State;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
@Builder
public class UserDto {
    private UUID uuid;
    private State.Value state;
    private PersonalInfoDto user;

    private ContractDto contractDto;
    private ExtraDataDto extraDataDto;
    private PersonalInfoDto approver;

    public UserDto setContractDto(final ContractDto contractDto) {
        this.contractDto = contractDto;
        return this;
    }

    public UserDto setExtraDataDto(final ExtraDataDto extraDataDto) {
        this.extraDataDto = extraDataDto;
        return this;
    }

    public UserDto setApprover(final PersonalInfoDto approver) {
        this.approver = approver;
        return this;
    }

}
