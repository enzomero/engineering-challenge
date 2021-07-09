package eu.senla.status_gate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangeRequestDto {
    private UUID uuid;
    private String extraData;
    private String contract;
    private boolean approver;
}
