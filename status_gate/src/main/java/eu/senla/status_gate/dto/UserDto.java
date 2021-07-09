package eu.senla.status_gate.dto;

import lombok.*;

import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class UserDto {
    private UUID uuid;
    private String personalInfo;
    private UserState userState = UserState.ADDED;
    private String contract;
    private String extraData;
    private Boolean approver;

    @Builder
    private UserDto(final UUID uuid, final String personalInfo) {
        this.uuid = uuid;
        this.personalInfo = personalInfo;
    }

    public UserDto setContract(final String contract) {
        if (contract == null)
            return this;
        this.contract = contract;
        this.userState = userState.setState(this);
        return this;
    }

    public UserDto setExtraData(final String extraData) {
        if (extraData == null)
            return this;
        this.extraData = extraData;
        this.userState = userState.setState(this);
        return this;
    }

    public UserDto setApprover(final Boolean approver) {
        if (approver == null)
            return this;
        this.approver = approver;
        this.userState = userState.setState(this);
        return this;
    }

}
