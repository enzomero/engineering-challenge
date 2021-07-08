package eu.senla.status_api.dto;

import eu.senla.status_api.dto.api.State;
import lombok.*;

import java.util.UUID;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
